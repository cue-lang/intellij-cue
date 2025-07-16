package dev.monogon.cue.lang.injection;

import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import dev.monogon.cue.CueLightTest;
import dev.monogon.cue.lang.psi.CueSimpleBytesLit;
import dev.monogon.cue.lang.psi.CueStringLiteral;
import org.intellij.plugins.intelliLang.inject.InjectedLanguage;
import org.intellij.plugins.intelliLang.inject.TemporaryPlacesRegistry;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

public class CueLanguageInjectionPerformerTest extends CueLightTest {
    @Test
    public void singleRange() {
        createCueFile("'cont<caret>ent'");
        var literal = findTypedElement(CueSimpleBytesLit.class);
        var ranges = CueLanguageInjectionPerformer.findInjectionRanges(literal);
        assertEquals(Collections.singletonList(new InjectionData(TextRange.create(1, 8), null, null)), ranges);
    }

    @Test
    public void interpolationMiddle() {
        createCueFile("'a\\(123)b'");
        var literal = findTypedElement(CueStringLiteral.class);
        var ranges = CueLanguageInjectionPerformer.findInjectionRanges(literal);

        var expected = Arrays.asList(
            new InjectionData(TextRange.create(1, 2), null, null),
            new InjectionData(TextRange.create(8, 9), "\\(123)", null));
        assertEquals(expected, ranges);
    }

    @Test
    public void interpolationFirst() {
        createCueFile("'\\(123)b'");
        var literal = findTypedElement(CueStringLiteral.class);
        var ranges = CueLanguageInjectionPerformer.findInjectionRanges(literal);

        var expected = Arrays.asList(
            new InjectionData(TextRange.create(1, 1), null, null),
            new InjectionData(TextRange.create(7, 8), "\\(123)", null));
        assertEquals(expected, ranges);
    }

    @Test
    public void interpolationEnd() {
        createCueFile("'a\\(123)'");
        var literal = findTypedElement(CueStringLiteral.class);
        var ranges = CueLanguageInjectionPerformer.findInjectionRanges(literal);

        var expected = Arrays.asList(
            new InjectionData(TextRange.create(1, 2), null, null),
            new InjectionData(TextRange.create(8, 8), "\\(123)", null));
        assertEquals(expected, ranges);
    }

    @Test
    public void interpolationMultiple() {
        createCueFile("'a\\(1)b\\(2)b\\(3)'");
        var literal = findTypedElement(CueStringLiteral.class);
        var ranges = CueLanguageInjectionPerformer.findInjectionRanges(literal);

        var expected = Arrays.asList(
            new InjectionData(TextRange.create(1, 2), null, null),
            new InjectionData(TextRange.create(6, 7), "\\(1)", null),
            new InjectionData(TextRange.create(11, 12), "\\(2)", null),
            new InjectionData(TextRange.create(16, 16), "\\(3)", null));
        assertEquals(expected, ranges);
    }

    @Test
    public void fragmentEditorEmpty() {
        var file = createCueFile("'''\n<caret>'''");
        withInjectedContent(file, fragmentFile -> {
            edit(fragmentFile, doc -> {
                doc.insertString(0, "a");
                doc.insertString(1, "b");
                doc.insertString(2, "c");
                doc.insertString(3, "\n");
            });

            myFixture.checkResult("'''\nabc\n'''");
        });
    }

    @Test
    public void fragmentEditorAppending() {
        var file = createCueFile("'''\ntest<caret>\n'''");
        withInjectedContent(file, fragmentFile -> {
            edit(fragmentFile, doc -> {
                doc.insertString(4, "\n");
                doc.insertString(5, "a");
                doc.insertString(6, "b");
                doc.insertString(7, "c");
            });

            myFixture.checkResult("'''\ntest\nabc\n'''");
        });
    }

    private void withInjectedContent(PsiFile file, Consumer<PsiFile> action) {
        var host = findTypedElement(CueStringLiteral.class);
        TemporaryPlacesRegistry.getInstance(getProject()).addHostWithUndo(host, InjectedLanguage.create("JSON"));
        disposeOnTearDown(() -> TemporaryPlacesRegistry.getInstance(getProject()).removeHostWithUndo(getProject(), host));

        var quickEdit = new QuickEditAction();
        var handler = quickEdit.invokeImpl(getProject(), myFixture.getEditor(), file);
        var fragmentFile = handler.getNewFile();

        action.accept(fragmentFile);
    }

    private void edit(PsiFile file, Consumer<Document> action) {
        CommandProcessor.getInstance().executeCommand(file.getProject(), () -> {
            ApplicationManager.getApplication().runWriteAction(() -> {
                var doc = PsiDocumentManager.getInstance(getProject()).getDocument(file);
                action.accept(doc);
            });
        }, "Change Doc", "Change doc");
    }
}