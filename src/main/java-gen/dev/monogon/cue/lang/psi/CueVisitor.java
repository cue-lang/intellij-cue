// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiLanguageInjectionHost;

public class CueVisitor extends PsiElementVisitor {

  public void visitAliasExpr(@NotNull CueAliasExpr o) {
    visitExpression(o);
  }

  public void visitArgument(@NotNull CueArgument o) {
    visitPrimaryExpr(o);
  }

  public void visitArguments(@NotNull CueArguments o) {
    visitPrimaryExpr(o);
  }

  public void visitBasicLit(@NotNull CueBasicLit o) {
    visitLiteral(o);
  }

  public void visitBinaryExpr(@NotNull CueBinaryExpr o) {
    visitExpression(o);
  }

  public void visitClause(@NotNull CueClause o) {
    visitCompositeElement(o);
  }

  public void visitClauses(@NotNull CueClauses o) {
    visitCompositeElement(o);
  }

  public void visitComprehension(@NotNull CueComprehension o) {
    visitCompositeElement(o);
  }

  public void visitDeclaration(@NotNull CueDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitElementList(@NotNull CueElementList o) {
    visitCompositeElement(o);
  }

  public void visitEllipsis(@NotNull CueEllipsis o) {
    visitCompositeElement(o);
  }

  public void visitEmbedding(@NotNull CueEmbedding o) {
    visitDeclaration(o);
  }

  public void visitExpression(@NotNull CueExpression o) {
    visitCompositeElement(o);
  }

  public void visitFallbackClause(@NotNull CueFallbackClause o) {
    visitCompositeElement(o);
  }

  public void visitField(@NotNull CueField o) {
    visitDeclaration(o);
  }

  public void visitForClause(@NotNull CueForClause o) {
    visitBlock(o);
  }

  public void visitFunc(@NotNull CueFunc o) {
    visitOperand(o);
  }

  public void visitFuncParam(@NotNull CueFuncParam o) {
    visitCompositeElement(o);
  }

  public void visitFuncParams(@NotNull CueFuncParams o) {
    visitCompositeElement(o);
  }

  public void visitGuardClause(@NotNull CueGuardClause o) {
    visitCompositeElement(o);
  }

  public void visitImportDecl(@NotNull CueImportDecl o) {
    visitCompositeElement(o);
  }

  public void visitImportLocation(@NotNull CueImportLocation o) {
    visitCompositeElement(o);
  }

  public void visitImportPath(@NotNull CueImportPath o) {
    visitCompositeElement(o);
  }

  public void visitImportSpec(@NotNull CueImportSpec o) {
    visitCompositeElement(o);
  }

  public void visitIndex(@NotNull CueIndex o) {
    visitPrimaryExpr(o);
  }

  public void visitLabel(@NotNull CueLabel o) {
    visitCompositeElement(o);
  }

  public void visitLabelExpr(@NotNull CueLabelExpr o) {
    visitCompositeElement(o);
  }

  public void visitLabelName(@NotNull CueLabelName o) {
    visitCompositeElement(o);
  }

  public void visitLetClause(@NotNull CueLetClause o) {
    visitBlock(o);
  }

  public void visitListLit(@NotNull CueListLit o) {
    visitLiteral(o);
  }

  public void visitLiteral(@NotNull CueLiteral o) {
    visitOperand(o);
  }

  public void visitOperand(@NotNull CueOperand o) {
    visitPrimaryExpr(o);
  }

  public void visitOperandName(@NotNull CueOperandName o) {
    visitOperand(o);
  }

  public void visitPackageClause(@NotNull CuePackageClause o) {
    visitCompositeElement(o);
  }

  public void visitPostfixAlias(@NotNull CuePostfixAlias o) {
    visitCompositeElement(o);
  }

  public void visitPrimaryExpr(@NotNull CuePrimaryExpr o) {
    visitExpression(o);
  }

  public void visitQualifiedIdent(@NotNull CueQualifiedIdent o) {
    visitOperand(o);
  }

  public void visitSelector(@NotNull CueSelector o) {
    visitPrimaryExpr(o);
  }

  public void visitSpread(@NotNull CueSpread o) {
    visitPrimaryExpr(o);
  }

  public void visitStartClause(@NotNull CueStartClause o) {
    visitCompositeElement(o);
  }

  public void visitStructLit(@NotNull CueStructLit o) {
    visitLiteral(o);
    // visitBlock(o);
  }

  public void visitTryClause(@NotNull CueTryClause o) {
    visitCompositeElement(o);
  }

  public void visitUnaryExpr(@NotNull CueUnaryExpr o) {
    visitExpression(o);
  }

  public void visitUnificationBinaryExpr(@NotNull CueUnificationBinaryExpr o) {
    visitBinaryExpr(o);
  }

  public void visitAttrTokens(@NotNull CueAttrTokens o) {
    visitCompositeElement(o);
  }

  public void visitAttribute(@NotNull CueAttribute o) {
    visitCompositeElement(o);
  }

  public void visitInterpolation(@NotNull CueInterpolation o) {
    visitCompositeElement(o);
  }

  public void visitMultilineBytesLit(@NotNull CueMultilineBytesLit o) {
    visitLiteral(o);
    // visitMultilineLiteral(o);
    // visitPsiLanguageInjectionHost(o);
  }

  public void visitMultilineStringLit(@NotNull CueMultilineStringLit o) {
    visitLiteral(o);
    // visitMultilineLiteral(o);
    // visitPsiLanguageInjectionHost(o);
  }

  public void visitSimpleBytesLit(@NotNull CueSimpleBytesLit o) {
    visitLiteral(o);
    // visitStringLiteral(o);
    // visitPsiLanguageInjectionHost(o);
  }

  public void visitSimpleStringLit(@NotNull CueSimpleStringLit o) {
    visitLiteral(o);
    // visitStringLiteral(o);
    // visitPsiLanguageInjectionHost(o);
  }

  public void visitBlock(@NotNull CueBlock o) {
    visitCompositeElement(o);
  }

  public void visitCompositeElement(@NotNull CueCompositeElement o) {
    visitElement(o);
  }

}
