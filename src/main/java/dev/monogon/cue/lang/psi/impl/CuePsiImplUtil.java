package dev.monogon.cue.lang.psi.impl;

import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import dev.monogon.cue.lang.CueTypes;
import dev.monogon.cue.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CuePsiImplUtil {
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
        if (!(parent instanceof CueLabelExpr)) {
            return false;
        }

        var next = name.getNextSibling();
        return next != null && next.getNode().getElementType() == CueTypes.QMARK;
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
                        } else if (field.getExpression() instanceof CueStructLit) {
                            //The syntax is json like
                            return AllIcons.Json.Object;
                        } else if (field.getExpression() instanceof CueAliasExpr) {
                            return AllIcons.Nodes.Alias;
                        } else if (field.getExpression() instanceof CueListLit) {
                            return AllIcons.Json.Array;
                        } else if (field.getExpression() instanceof CueUnaryExpr) {
                            return AllIcons.Nodes.Function;
                        } else {
                            return AllIcons.Nodes.Property;
                        }
                    }
                }
        ).orElse(null);
    }
}
