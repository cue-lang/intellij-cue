// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.monogon.cue.lang.CueTypes.*;
import dev.monogon.cue.lang.psi.*;

public class CueSimpleBytesLitImpl extends CueSimpleByteLiteralMixin implements CueSimpleBytesLit {

  public CueSimpleBytesLitImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull CueVisitor visitor) {
    visitor.visitSimpleBytesLit(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CueVisitor) accept((CueVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CueInterpolation> getInterpolationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CueInterpolation.class);
  }

  @Override
  @NotNull
  public PsiElement getOpeningQuote() {
    return findNotNullChildByType(SINGLE_QUOTE);
  }

  @Override
  @Nullable
  public PsiElement getClosingQuote() {
    return findChildByType(SINGLE_QUOTE_END);
  }

}
