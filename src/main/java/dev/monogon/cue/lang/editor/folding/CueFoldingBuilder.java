package dev.monogon.cue.lang.editor.folding;

import com.intellij.codeInsight.folding.CodeFoldingSettings;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.SmartList;
import dev.monogon.cue.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CueFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        var settings = CueFoldingSettingsService.getSettings();

        List<FoldingDescriptor> result = new SmartList<>();
        SyntaxTraverser.psiTraverser(root).forEach(e -> foldElement(e, result, settings));
        return result.toArray(new FoldingDescriptor[0]);
    }

    private void foldElement(@NotNull PsiElement element, @NotNull List<FoldingDescriptor> result, @NotNull CueFoldingSettings settings) {
        if (element instanceof CueFile) {
            foldFileImports((CueFile)element, settings, result);
        }
        if (element instanceof PsiComment) {
            foldBlock((PsiComment)element, settings, result);
        }
        if (element instanceof CueImportDecl) {
            foldImportGroup((CueImportDecl)element, settings, result);
        }
        if (element instanceof CueStructLit) {
            foldStruct((CueStructLit)element, result);
        }
        if (element instanceof CueListLit) {
            foldList((CueListLit)element, result);
        }
        if (element instanceof CueInterpolation) {
            foldInterpolation((CueInterpolation)element, result);
        }
        if (element instanceof CueMultilineLiteral) {
            foldMultilineLiteral((CueMultilineLiteral)element, result);
        }
        if (element instanceof CueAttribute) {
            foldAttribute((CueAttribute)element, result);
        }
    }

    private void foldAttribute(CueAttribute attribute, List<FoldingDescriptor> result) {
        if (attribute.getRightParen() == null) {
            return;
        }

        result.add(new FoldingDescriptor(attribute.getNode(), attribute.getTextRange(), null, "@" + attribute.getAttributeName()));
    }

    private void foldMultilineLiteral(CueMultilineLiteral literal, List<FoldingDescriptor> result) {
        if (literal.getOpeningQuote() == null) {
            return;
        }

        var placeholder = (literal instanceof CueMultilineBytesLit) ? "'...'" : "\"...\"";
        result.add(new FoldingDescriptor(literal.getNode(), literal.getTextRange(), null, placeholder));
    }

    private void foldInterpolation(CueInterpolation interpolation, List<FoldingDescriptor> result) {
        result.add(new FoldingDescriptor(interpolation.getNode(), interpolation.getTextRange(), null, "\\(...)", false, Set.of()));
    }

    private void foldBlock(PsiComment element, CueFoldingSettings settings, List<FoldingDescriptor> result) {
        //only old if it's the first comment of a block
        if (PsiTreeUtil.prevLeaf(element) instanceof PsiComment) {
            return;
        }

        var next = PsiTreeUtil.nextLeaf(element);
        if (!(next instanceof PsiComment)) {
            // not a multiline comment
            return;
        }

        while (true) {
            var nextLeaf = PsiTreeUtil.nextLeaf(next);
            if (!(nextLeaf instanceof PsiComment)) {
                break;
            }
            next = nextLeaf;
        }

        var ideSettings = CodeFoldingSettings.getInstance();
        var start = element.getTextRange().getStartOffset();
        var end = next.getTextRange().getEndOffset() - (next.getText().endsWith("\n") ? 1 : 0);
        var range = TextRange.create(start, end);

        var placeholder = StringUtil.trimEnd(element.getText(), '\n') + " ...";
        var collapsedByDefault = ideSettings.COLLAPSE_FILE_HEADER && PsiTreeUtil.prevLeaf(element) == null
                                 || ideSettings.COLLAPSE_DOC_COMMENTS;
        result.add(new FoldingDescriptor(element.getNode(), range, null, placeholder, collapsedByDefault, Collections.emptySet()));
    }

    private void foldFileImports(@NotNull CueFile file, @NotNull CueFoldingSettings settings, List<FoldingDescriptor> result) {
        var imports = file.getImportDeclarations();
        if (imports.size() >= 2) {
            var ideSettings = CodeFoldingSettings.getInstance();
            var first = imports.get(0);
            var last = imports.get(imports.size() - 1);
            var range = TextRange.create(first.getTextRange().getStartOffset(), last.getTextRange().getEndOffset());

            result.add(new FoldingDescriptor(file.getNode(), range, null, "import ...", ideSettings.COLLAPSE_IMPORTS, Set.of()));
        }
    }

    private void foldImportGroup(@NotNull CueImportDecl element, @NotNull CueFoldingSettings settings, List<FoldingDescriptor> result) {
        var imports = element.getImportSpecList();
        var left = element.getLeftParen();
        var right = element.getRightParen();
        if (!imports.isEmpty() && left != null && right != null) {
            var range = TextRange.create(left.getTextRange().getStartOffset(), right.getTextRange().getEndOffset());
            result.add(new FoldingDescriptor(element.getNode(), range, null, "(...)", settings.foldImportGroups, Collections.emptySet()));
        }
    }

    private void foldList(CueListLit list, List<FoldingDescriptor> result) {
        var rightBracket = list.getRightBracket();
        if (rightBracket != null) {
            var start = list.getLeftBracket().getTextRange().getStartOffset();
            var end = rightBracket.getTextRange().getEndOffset();
            result.add(new FoldingDescriptor(list.getNode(), TextRange.create(start, end), null, "[...]"));
        }
    }

    private void foldStruct(@NotNull CueStructLit struct, List<FoldingDescriptor> result) {
        var rightBrace = struct.getRightBrace();
        if (rightBrace != null) {
            var start = struct.getLeftBrace().getTextRange().getStartOffset();
            var end = rightBrace.getTextRange().getEndOffset();
            result.add(new FoldingDescriptor(struct.getNode(), TextRange.create(start, end), null, "{...}"));
        }
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        // we're already adding the collapsed text with the marker definitions
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        // we're already setting the default state with the marker definitions, based on the cue folding settings
        return false;
    }
}