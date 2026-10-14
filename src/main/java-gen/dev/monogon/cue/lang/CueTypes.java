// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.monogon.cue.lang.psi.CueCompositeElementType;
import dev.monogon.cue.lang.psi.impl.*;

public interface CueTypes {

  IElementType ALIAS_EXPR = new CueCompositeElementType("ALIAS_EXPR");
  IElementType ARGUMENT = new CueCompositeElementType("ARGUMENT");
  IElementType ARGUMENTS = new CueCompositeElementType("ARGUMENTS");
  IElementType ATTRIBUTE = new CueCompositeElementType("ATTRIBUTE");
  IElementType ATTR_TOKENS = new CueCompositeElementType("ATTR_TOKENS");
  IElementType BASIC_LIT = new CueCompositeElementType("BASIC_LIT");
  IElementType BINARY_EXPR = new CueCompositeElementType("BINARY_EXPR");
  IElementType CLAUSE = new CueCompositeElementType("CLAUSE");
  IElementType CLAUSES = new CueCompositeElementType("CLAUSES");
  IElementType COMPREHENSION = new CueCompositeElementType("COMPREHENSION");
  IElementType DECLARATION = new CueCompositeElementType("DECLARATION");
  IElementType ELEMENT_LIST = new CueCompositeElementType("ELEMENT_LIST");
  IElementType ELLIPSIS = new CueCompositeElementType("ELLIPSIS");
  IElementType EMBEDDING = new CueCompositeElementType("EMBEDDING");
  IElementType EXPRESSION = new CueCompositeElementType("EXPRESSION");
  IElementType FIELD = new CueCompositeElementType("FIELD");
  IElementType FOR_CLAUSE = new CueCompositeElementType("FOR_CLAUSE");
  IElementType GUARD_CLAUSE = new CueCompositeElementType("GUARD_CLAUSE");
  IElementType IMPORT_DECL = new CueCompositeElementType("IMPORT_DECL");
  IElementType IMPORT_LOCATION = new CueCompositeElementType("IMPORT_LOCATION");
  IElementType IMPORT_PATH = new CueCompositeElementType("IMPORT_PATH");
  IElementType IMPORT_SPEC = new CueCompositeElementType("IMPORT_SPEC");
  IElementType INDEX = new CueCompositeElementType("INDEX");
  IElementType INTERPOLATION = new CueCompositeElementType("INTERPOLATION");
  IElementType LABEL = new CueCompositeElementType("LABEL");
  IElementType LABEL_EXPR = new CueCompositeElementType("LABEL_EXPR");
  IElementType LABEL_NAME = new CueCompositeElementType("LABEL_NAME");
  IElementType LET_CLAUSE = new CueCompositeElementType("LET_CLAUSE");
  IElementType LIST_LIT = new CueCompositeElementType("LIST_LIT");
  IElementType LITERAL = new CueCompositeElementType("LITERAL");
  IElementType MULTILINE_BYTES_LIT = new CueCompositeElementType("MULTILINE_BYTES_LIT");
  IElementType MULTILINE_STRING_LIT = new CueCompositeElementType("MULTILINE_STRING_LIT");
  IElementType OPERAND = new CueCompositeElementType("OPERAND");
  IElementType OPERAND_NAME = new CueCompositeElementType("OPERAND_NAME");
  IElementType PACKAGE_CLAUSE = new CueCompositeElementType("PACKAGE_CLAUSE");
  IElementType POSTFIX_ALIAS = new CueCompositeElementType("POSTFIX_ALIAS");
  IElementType PRIMARY_EXPR = new CueCompositeElementType("PRIMARY_EXPR");
  IElementType QUALIFIED_IDENT = new CueCompositeElementType("QUALIFIED_IDENT");
  IElementType SELECTOR = new CueCompositeElementType("SELECTOR");
  IElementType SIMPLE_BYTES_LIT = new CueCompositeElementType("SIMPLE_BYTES_LIT");
  IElementType SIMPLE_STRING_LIT = new CueCompositeElementType("SIMPLE_STRING_LIT");
  IElementType SPREAD = new CueCompositeElementType("SPREAD");
  IElementType START_CLAUSE = new CueCompositeElementType("START_CLAUSE");
  IElementType STRUCT_LIT = new CueCompositeElementType("STRUCT_LIT");
  IElementType UNARY_EXPR = new CueCompositeElementType("UNARY_EXPR");
  IElementType UNIFICATION_BINARY_EXPR = new CueCompositeElementType("UNIFICATION_BINARY_EXPR");

  IElementType ADD_OP = new CueTokenType("ADD_OP");
  IElementType ARROW = new CueTokenType("->");
  IElementType AT = new CueTokenType("@");
  IElementType ATTRIBUTE_VALUE = new CueTokenType("ATTRIBUTE_VALUE");
  IElementType BOOL_LIT = new CueTokenType("BOOL_LIT");
  IElementType BOTTOM_LIT = new CueTokenType("_|_");
  IElementType BYTE_VALUE = new CueTokenType("BYTE_VALUE");
  IElementType COLON = new CueTokenType(":");
  IElementType COMMA = new CueTokenType(",");
  IElementType DOT = new CueTokenType(".");
  IElementType DOUBLE_QUOTE = new CueTokenType("DOUBLE_QUOTE");
  IElementType DOUBLE_QUOTE_END = new CueTokenType("\"");
  IElementType ELLIPSIS_TOKEN = new CueTokenType("...");
  IElementType EQ = new CueTokenType("=");
  IElementType ESCAPED_CHAR = new CueTokenType("ESCAPED_CHAR");
  IElementType EXCL = new CueTokenType("!");
  IElementType FLOAT_LIT = new CueTokenType("FLOAT_LIT");
  IElementType HASH = new CueTokenType("#");
  IElementType IDENTIFIER = new CueTokenType("IDENTIFIER");
  IElementType IDENTIFIER_PREDECLARED = new CueTokenType("IDENTIFIER_PREDECLARED");
  IElementType INTERPOLATION_END = new CueTokenType("INTERPOLATION_END");
  IElementType INTERPOLATION_START = new CueTokenType("INTERPOLATION_START");
  IElementType INT_LIT = new CueTokenType("INT_LIT");
  IElementType KEYWORD = new CueTokenType("KEYWORD");
  IElementType LEFT_BRACKET = new CueTokenType("[");
  IElementType LEFT_CURLY = new CueTokenType("{");
  IElementType LEFT_PAREN = new CueTokenType("(");
  IElementType MULTILINE_BYTES_END = new CueTokenType("'''");
  IElementType MULTILINE_BYTES_START = new CueTokenType("MULTILINE_BYTES_START");
  IElementType MULTILINE_STRING_END = new CueTokenType("\"\"\"");
  IElementType MULTILINE_STRING_START = new CueTokenType("MULTILINE_STRING_START");
  IElementType MUL_OP = new CueTokenType("MUL_OP");
  IElementType NEWLINE = new CueTokenType("\\n");
  IElementType NULL_LIT = new CueTokenType("NULL_LIT");
  IElementType OP_AND = new CueTokenType("&&");
  IElementType OP_DISJUNCTION = new CueTokenType("|");
  IElementType OP_EQ = new CueTokenType("==");
  IElementType OP_OR = new CueTokenType("||");
  IElementType OP_UNIFICATION = new CueTokenType("&");
  IElementType QMARK = new CueTokenType("?");
  IElementType REL_OP = new CueTokenType("REL_OP");
  IElementType RIGHT_BRACKET = new CueTokenType("]");
  IElementType RIGHT_CURLY = new CueTokenType("}");
  IElementType RIGHT_PAREN = new CueTokenType(")");
  IElementType SINGLE_QUOTE = new CueTokenType("SINGLE_QUOTE");
  IElementType SINGLE_QUOTE_END = new CueTokenType("'");
  IElementType TILDE = new CueTokenType("~");
  IElementType UNICODE_VALUE = new CueTokenType("UNICODE_VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALIAS_EXPR) {
        return new CueAliasExprImpl(node);
      }
      else if (type == ARGUMENTS) {
        return new CueArgumentsImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new CueAttributeImpl(node);
      }
      else if (type == ATTR_TOKENS) {
        return new CueAttrTokensImpl(node);
      }
      else if (type == BASIC_LIT) {
        return new CueBasicLitImpl(node);
      }
      else if (type == BINARY_EXPR) {
        return new CueBinaryExprImpl(node);
      }
      else if (type == CLAUSE) {
        return new CueClauseImpl(node);
      }
      else if (type == CLAUSES) {
        return new CueClausesImpl(node);
      }
      else if (type == COMPREHENSION) {
        return new CueComprehensionImpl(node);
      }
      else if (type == DECLARATION) {
        return new CueDeclarationImpl(node);
      }
      else if (type == ELEMENT_LIST) {
        return new CueElementListImpl(node);
      }
      else if (type == ELLIPSIS) {
        return new CueEllipsisImpl(node);
      }
      else if (type == EMBEDDING) {
        return new CueEmbeddingImpl(node);
      }
      else if (type == FIELD) {
        return new CueFieldImpl(node);
      }
      else if (type == FOR_CLAUSE) {
        return new CueForClauseImpl(node);
      }
      else if (type == GUARD_CLAUSE) {
        return new CueGuardClauseImpl(node);
      }
      else if (type == IMPORT_DECL) {
        return new CueImportDeclImpl(node);
      }
      else if (type == IMPORT_LOCATION) {
        return new CueImportLocationImpl(node);
      }
      else if (type == IMPORT_PATH) {
        return new CueImportPathImpl(node);
      }
      else if (type == IMPORT_SPEC) {
        return new CueImportSpecImpl(node);
      }
      else if (type == INDEX) {
        return new CueIndexImpl(node);
      }
      else if (type == INTERPOLATION) {
        return new CueInterpolationImpl(node);
      }
      else if (type == LABEL) {
        return new CueLabelImpl(node);
      }
      else if (type == LABEL_EXPR) {
        return new CueLabelExprImpl(node);
      }
      else if (type == LABEL_NAME) {
        return new CueLabelNameImpl(node);
      }
      else if (type == LET_CLAUSE) {
        return new CueLetClauseImpl(node);
      }
      else if (type == LIST_LIT) {
        return new CueListLitImpl(node);
      }
      else if (type == MULTILINE_BYTES_LIT) {
        return new CueMultilineBytesLitImpl(node);
      }
      else if (type == MULTILINE_STRING_LIT) {
        return new CueMultilineStringLitImpl(node);
      }
      else if (type == OPERAND) {
        return new CueOperandImpl(node);
      }
      else if (type == OPERAND_NAME) {
        return new CueOperandNameImpl(node);
      }
      else if (type == PACKAGE_CLAUSE) {
        return new CuePackageClauseImpl(node);
      }
      else if (type == POSTFIX_ALIAS) {
        return new CuePostfixAliasImpl(node);
      }
      else if (type == PRIMARY_EXPR) {
        return new CuePrimaryExprImpl(node);
      }
      else if (type == QUALIFIED_IDENT) {
        return new CueQualifiedIdentImpl(node);
      }
      else if (type == SELECTOR) {
        return new CueSelectorImpl(node);
      }
      else if (type == SIMPLE_BYTES_LIT) {
        return new CueSimpleBytesLitImpl(node);
      }
      else if (type == SIMPLE_STRING_LIT) {
        return new CueSimpleStringLitImpl(node);
      }
      else if (type == SPREAD) {
        return new CueSpreadImpl(node);
      }
      else if (type == START_CLAUSE) {
        return new CueStartClauseImpl(node);
      }
      else if (type == STRUCT_LIT) {
        return new CueStructLitImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new CueUnaryExprImpl(node);
      }
      else if (type == UNIFICATION_BINARY_EXPR) {
        return new CueUnificationBinaryExprImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
