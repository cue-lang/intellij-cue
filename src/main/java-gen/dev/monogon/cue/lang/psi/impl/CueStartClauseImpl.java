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

public class CueStartClauseImpl extends CueCompositeElementImpl implements CueStartClause {

  public CueStartClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CueVisitor visitor) {
    visitor.visitStartClause(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CueVisitor) accept((CueVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CueForClause getForClause() {
    return findChildByClass(CueForClause.class);
  }

  @Override
  @Nullable
  public CueGuardClause getGuardClause() {
    return findChildByClass(CueGuardClause.class);
  }

  @Override
  @Nullable
  public CueTryClause getTryClause() {
    return findChildByClass(CueTryClause.class);
  }

}
