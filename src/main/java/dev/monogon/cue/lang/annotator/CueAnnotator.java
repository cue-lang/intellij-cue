package dev.monogon.cue.lang.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import dev.monogon.cue.lang.highlighter.CueHighlightingColors;
import dev.monogon.cue.lang.psi.CueAttribute;
import dev.monogon.cue.lang.psi.CueLabelName;
import org.jetbrains.annotations.NotNull;

public class CueAnnotator implements Annotator, DumbAware {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof CueLabelName) {
            annotateLabelName((CueLabelName)element, holder);
        }

        if (element instanceof CueAttribute) {
            annotateAttribute((CueAttribute)element, holder);
        }
    }

    private static void annotateLabelName(@NotNull CueLabelName element, @NotNull AnnotationHolder holder) {
        var baseAttributeKey = element.isDynamicFieldName()
                               ? CueHighlightingColors.FIELD_NAME_DYNAMIC
                               : CueHighlightingColors.FIELD_NAME;

        TextAttributesKey constraintAttributeKey;
        if (element.isOptionalFieldName()) {
            constraintAttributeKey = CueHighlightingColors.FIELD_NAME_OPTIONAL;
        }
        else if (element.isRequiredFieldName()) {
            constraintAttributeKey = CueHighlightingColors.FIELD_NAME_REQUIRED;
        }
        else {
            constraintAttributeKey = null;
        }

        if (constraintAttributeKey == null) {
            // no merge needed
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(baseAttributeKey).create();
        }
        else {
            // add constraint attributes on top of base attribute
            var scheme = EditorColorsManager.getInstance().getGlobalScheme();
            var base = scheme.getAttributes(baseAttributeKey);
            var constraint = scheme.getAttributes(constraintAttributeKey);
            var merged = TextAttributes.merge(base, constraint);
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).enforcedTextAttributes(merged).create();
        }
    }

    private static void annotateAttribute(@NotNull CueAttribute element, @NotNull AnnotationHolder holder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getAttributeNameElement())
            .textAttributes(CueHighlightingColors.ATTRIBUTE_NAME)
            .create();
    }
}
