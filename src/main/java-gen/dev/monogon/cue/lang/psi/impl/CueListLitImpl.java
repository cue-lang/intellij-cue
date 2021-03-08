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

public class CueListLitImpl extends CueLiteralImpl implements CueListLit {

  public CueListLitImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull CueVisitor visitor) {
    visitor.visitListLit(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CueVisitor) accept((CueVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CueElementList getElementList() {
    return findChildByClass(CueElementList.class);
  }

  @Override
  @Nullable
  public CueEllipsis getEllipsis() {
    return findChildByClass(CueEllipsis.class);
  }

  @Override
  @NotNull
  public PsiElement getLeftBracket() {
    return findNotNullChildByType(LEFT_BRACKET);
  }

  @Override
  @Nullable
  public PsiElement getRightBracket() {
    return findChildByType(RIGHT_BRACKET);
  }

}
