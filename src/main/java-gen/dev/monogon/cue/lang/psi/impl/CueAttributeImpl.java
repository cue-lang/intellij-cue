// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.monogon.cue.lang.CueTypes.*;
import dev.monogon.cue.lang.psi.CueCompositeElementImpl;
import dev.monogon.cue.lang.psi.*;

public class CueAttributeImpl extends CueCompositeElementImpl implements CueAttribute {

  public CueAttributeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CueVisitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CueVisitor) accept((CueVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CueAttrTokens getAttrTokens() {
    return findChildByClass(CueAttrTokens.class);
  }

  @Override
  @Nullable
  public PsiElement getLeftParen() {
    return findChildByType(LEFT_PAREN);
  }

  @Override
  @Nullable
  public PsiElement getRightParen() {
    return findChildByType(RIGHT_PAREN);
  }

  @Override
  public @NotNull PsiElement getAttributeNameElement() {
    return CuePsiImplUtil.getAttributeNameElement(this);
  }

  @Override
  public @NotNull String getAttributeName() {
    return CuePsiImplUtil.getAttributeName(this);
  }

}
