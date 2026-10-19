// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CueFunc extends CueOperand {

  @NotNull
  List<CueExpression> getExpressionList();

  @NotNull
  CueFuncParams getFuncParams();

}
