package dev.monogon.cue.lang.psi.impl;

import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiUtilCore;
import dev.monogon.cue.lang.CueTypes;
import dev.monogon.cue.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CuePsiImplUtil {
    private static final TokenSet CONSTRAINT_MARKERS = TokenSet.create(CueTypes.QMARK, CueTypes.EXCL);

    private CuePsiImplUtil() {
    }

    /**
     * The attribute name IDENTIFIER token of a CUE attribute element. It's the token after the initial "@"
     */
    @NotNull
    public static PsiElement getAttributeNameElement(@NotNull CueAttribute attribute) {
        PsiElement e = attribute.getFirstChild().getNextSibling();
        assert e.getNode().getElementType() == CueTypes.IDENTIFIER || e.getNode().getElementType() == CueTypes.IDENTIFIER_PREDECLARED;
        return e;
    }

    /**
     * The string value of the attribute name element
     */
    @NotNull
    public static String getAttributeName(@NotNull CueAttribute attribute) {
        return getAttributeNameElement(attribute).getText();
    }

    public static boolean isOptionalFieldName(@NotNull CueLabelName name) {
        var parent = name.getParent();
        return parent instanceof CueLabelExpr && ((CueLabelExpr)parent).isOptionalFieldConstraint();
    }

    public static boolean isRequiredFieldName(@NotNull CueLabelName name) {
        var parent = name.getParent();
        return parent instanceof CueLabelExpr && ((CueLabelExpr)parent).isRequiredFieldConstraint();
    }

    public static boolean isDynamicFieldName(@NotNull CueLabelName name) {
        var first = name.getFirstChild();
        return first != null && PsiUtilCore.getElementType(first) == CueTypes.LEFT_PAREN
               && name.getExpression() != null;
    }

    /**
     * @return If the label is marked with a "?" token.
     */
    public static boolean isOptionalFieldConstraint(@NotNull CueLabelExpr label) {
        return getConstraintElementType(label) == CueTypes.QMARK;
    }

    /**
     * @return If the label is marked with a "!" token.
     */
    public static boolean isRequiredFieldConstraint(@NotNull CueLabelExpr label) {
        return getConstraintElementType(label) == CueTypes.EXCL;
    }

    private static @Nullable IElementType getConstraintElementType(@NotNull CueLabelExpr label) {
        var marker = label.getNode().findChildByType(CONSTRAINT_MARKERS);
        return marker != null ? marker.getElementType() : null;
    }

    public static @Nullable ItemPresentation getPresentation(@NotNull CueField field) {
        return field.getLabelList().stream().map(PsiElement::getText).reduce((x, y) -> x + "." + y).map(
            (name) -> new ItemPresentation() {

                @Override
                public String getPresentableText() {
                    return name;
                }

                @Nullable
                @Override
                public String getLocationString() {
                    return null;
                }


                @Override
                public Icon getIcon(boolean unused) {
                    if (name.startsWith("#") || name.startsWith("_#")) {
                        return AllIcons.Nodes.Type;
                    }
                    else if (field.getExpression() instanceof CueStructLit) {
                        //The syntax is json like
                        return AllIcons.Json.Object;
                    }
                    else if (field.getExpression() instanceof CueAliasExpr) {
                        return AllIcons.Nodes.Alias;
                    }
                    else if (field.getExpression() instanceof CueListLit) {
                        return AllIcons.Json.Array;
                    }
                    else if (field.getExpression() instanceof CueUnaryExpr) {
                        return AllIcons.Nodes.Function;
                    }
                    else {
                        return AllIcons.Nodes.Property;
                    }
                }
            }
        ).orElse(null);
    }
}
