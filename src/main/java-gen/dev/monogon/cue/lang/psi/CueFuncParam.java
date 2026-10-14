// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CueFuncParam extends CueCompositeElement {

  @NotNull
  List<CueExpression> getExpressionList();

  @Nullable
  CuePostfixAlias getPostfixAlias();

  @NotNull
  List<CueAttribute> getAttributeList();

}
