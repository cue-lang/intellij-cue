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

public class CueComprehensionImpl extends CueCompositeElementImpl implements CueComprehension {

  public CueComprehensionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CueVisitor visitor) {
    visitor.visitComprehension(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CueVisitor) accept((CueVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CueClauses getClauses() {
    return findNotNullChildByClass(CueClauses.class);
  }

  @Override
  @Nullable
  public CueFallbackClause getFallbackClause() {
    return findChildByClass(CueFallbackClause.class);
  }

  @Override
  @NotNull
  public CueStructLit getStructLit() {
    return findNotNullChildByClass(CueStructLit.class);
  }

}
