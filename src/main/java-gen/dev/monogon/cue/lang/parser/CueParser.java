// This is a generated file. Not intended for manual editing.
package dev.monogon.cue.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.monogon.cue.lang.CueTypes.*;
import static dev.monogon.cue.lang.parser.CueParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CueParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(BINARY_EXPR, EXPRESSION, UNARY_EXPR),
    create_token_set_(ARGUMENT, ARGUMENTS, INDEX, PRIMARY_EXPR,
      SELECTOR),
  };

  /* ********************************************************** */
  // Expression | IDENTIFIER "=" Expression
  public static boolean AliasExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ALIAS_EXPR, "<alias expr>");
    r = Expression(b, l + 1, -1);
    if (!r) r = AliasExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IDENTIFIER "=" Expression
  private static boolean AliasExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQ);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression
  public static boolean Argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT, "<argument>");
    r = Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "(" [ ( Argument { "," Argument }* ) [ "," ] ] ")"
  public static boolean Arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments")) return false;
    if (!nextTokenIsFast(b, LEFT_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && Arguments_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, ARGUMENTS, r);
    return r;
  }

  // [ ( Argument { "," Argument }* ) [ "," ] ]
  private static boolean Arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1")) return false;
    Arguments_1_0(b, l + 1);
    return true;
  }

  // ( Argument { "," Argument }* ) [ "," ]
  private static boolean Arguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Arguments_1_0_0(b, l + 1);
    r = r && Arguments_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Argument { "," Argument }*
  private static boolean Arguments_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Argument(b, l + 1);
    r = r && Arguments_1_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { "," Argument }*
  private static boolean Arguments_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Arguments_1_0_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Arguments_1_0_0_1", c)) break;
    }
    return true;
  }

  // "," Argument
  private static boolean Arguments_1_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COMMA);
    r = r && Argument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "," ]
  private static boolean Arguments_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_1")) return false;
    consumeTokenFast(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // INT_LIT | FLOAT_LIT | string_lit | NULL_LIT | BOOL_LIT | BOTTOM_LIT | TOP_LIT
  public static boolean BasicLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BasicLit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BASIC_LIT, "<basic lit>");
    r = consumeTokenFast(b, INT_LIT);
    if (!r) r = consumeTokenFast(b, FLOAT_LIT);
    if (!r) r = string_lit(b, l + 1);
    if (!r) r = consumeTokenFast(b, NULL_LIT);
    if (!r) r = consumeTokenFast(b, BOOL_LIT);
    if (!r) r = consumeTokenFast(b, BOTTOM_LIT);
    if (!r) r = consumeTokenFast(b, TOP_LIT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // StartClause | LetClause
  public static boolean Clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLAUSE, "<clause>");
    r = StartClause(b, l + 1);
    if (!r) r = LetClause(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // StartClause { [ "," ] Clause }*
  public static boolean Clauses(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLAUSES, "<clauses>");
    r = StartClause(b, l + 1);
    r = r && Clauses_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // { [ "," ] Clause }*
  private static boolean Clauses_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Clauses_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Clauses_1", c)) break;
    }
    return true;
  }

  // [ "," ] Clause
  private static boolean Clauses_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Clauses_1_0_0(b, l + 1);
    r = r && Clause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "," ]
  private static boolean Clauses_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_1_0_0")) return false;
    consumeTokenFast(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // Clauses StructLit
  public static boolean Comprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comprehension")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPREHENSION, "<comprehension>");
    r = Clauses(b, l + 1);
    r = r && StructLit(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Field | Ellipsis | Embedding | LetClause | attribute
  public static boolean Declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECLARATION, "<declaration>");
    r = Field(b, l + 1);
    if (!r) r = Ellipsis(b, l + 1);
    if (!r) r = Embedding(b, l + 1);
    if (!r) r = LetClause(b, l + 1);
    if (!r) r = attribute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Embedding { "," Embedding }*
  public static boolean ElementList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_LIST, "<element list>");
    r = Embedding(b, l + 1);
    r = r && ElementList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // { "," Embedding }*
  private static boolean ElementList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ElementList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ElementList_1", c)) break;
    }
    return true;
  }

  // "," Embedding
  private static boolean ElementList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COMMA);
    r = r && Embedding(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "..." [ Expression ]
  public static boolean Ellipsis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Ellipsis")) return false;
    if (!nextTokenIsFast(b, ELLIPSIS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, ELLIPSIS_TOKEN);
    r = r && Ellipsis_1(b, l + 1);
    exit_section_(b, m, ELLIPSIS, r);
    return r;
  }

  // [ Expression ]
  private static boolean Ellipsis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Ellipsis_1")) return false;
    Expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // Comprehension | AliasExpr
  public static boolean Embedding(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Embedding")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EMBEDDING, "<embedding>");
    r = Comprehension(b, l + 1);
    if (!r) r = AliasExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Label ":" { Label ":" }* Expression { attribute }*
  public static boolean Field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = Label(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Field_2(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    r = r && Field_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // { Label ":" }*
  private static boolean Field_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Field_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Field_2", c)) break;
    }
    return true;
  }

  // Label ":"
  private static boolean Field_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Label(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // { attribute }*
  private static boolean Field_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Field_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Field_4", c)) break;
    }
    return true;
  }

  // { attribute }
  private static boolean Field_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "for" IDENTIFIER [ "," IDENTIFIER ] "in" Expression
  public static boolean ForClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_CLAUSE, "<for clause>");
    r = consumeTokenFast(b, "for");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && ForClause_2(b, l + 1);
    r = r && consumeToken(b, "in");
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ "," IDENTIFIER ]
  private static boolean ForClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2")) return false;
    ForClause_2_0(b, l + 1);
    return true;
  }

  // "," IDENTIFIER
  private static boolean ForClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "if" Expression
  public static boolean GuardClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GuardClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GUARD_CLAUSE, "<guard clause>");
    r = consumeTokenFast(b, "if");
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "import" ( ImportSpec | "(" { ImportSpec "," }* ")" )
  public static boolean ImportDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_DECL, "<import decl>");
    r = consumeTokenFast(b, "import");
    r = r && ImportDecl_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ImportSpec | "(" { ImportSpec "," }* ")"
  private static boolean ImportDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    if (!r) r = ImportDecl_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" { ImportSpec "," }* ")"
  private static boolean ImportDecl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && ImportDecl_1_1_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // { ImportSpec "," }*
  private static boolean ImportDecl_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDecl_1_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportDecl_1_1_1", c)) break;
    }
    return true;
  }

  // ImportSpec ","
  private static boolean ImportDecl_1_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // { UNICODE_VALUE }*
  public static boolean ImportLocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportLocation")) return false;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_LOCATION, "<import location>");
    while (true) {
      int c = current_position_(b);
      if (!ImportLocation_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportLocation", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // { UNICODE_VALUE }
  private static boolean ImportLocation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportLocation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, UNICODE_VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "\"" ImportLocation [ ":" IDENTIFIER ] "\""
  public static boolean ImportPath(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_PATH, "<import path>");
    r = consumeTokenFast(b, "\"");
    r = r && ImportLocation(b, l + 1);
    r = r && ImportPath_2(b, l + 1);
    r = r && consumeToken(b, "\"");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ ":" IDENTIFIER ]
  private static boolean ImportPath_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_2")) return false;
    ImportPath_2_0(b, l + 1);
    return true;
  }

  // ":" IDENTIFIER
  private static boolean ImportPath_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [ PackageName ] ImportPath
  public static boolean ImportSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpec")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_SPEC, "<import spec>");
    r = ImportSpec_0(b, l + 1);
    r = r && ImportPath(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ PackageName ]
  private static boolean ImportSpec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpec_0")) return false;
    PackageName(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "[" Expression "]"
  public static boolean Index(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Index")) return false;
    if (!nextTokenIsFast(b, LEFT_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_BRACKET);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, INDEX, r);
    return r;
  }

  /* ********************************************************** */
  // [ IDENTIFIER "=" ] LabelExpr
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL, "<label>");
    r = Label_0(b, l + 1);
    r = r && LabelExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ IDENTIFIER "=" ]
  private static boolean Label_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0")) return false;
    Label_0_0(b, l + 1);
    return true;
  }

  // IDENTIFIER "="
  private static boolean Label_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQ);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LabelName [ "?" ] | "[" AliasExpr "]"
  public static boolean LabelExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_EXPR, "<label expr>");
    r = LabelExpr_0(b, l + 1);
    if (!r) r = LabelExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LabelName [ "?" ]
  private static boolean LabelExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LabelName(b, l + 1);
    r = r && LabelExpr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "?" ]
  private static boolean LabelExpr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0_1")) return false;
    consumeTokenFast(b, QMARK);
    return true;
  }

  // "[" AliasExpr "]"
  private static boolean LabelExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_BRACKET);
    r = r && AliasExpr(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | simple_string_lit
  public static boolean LabelName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelName")) return false;
    if (!nextTokenIsFast(b, DOUBLE_QUOTE, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_NAME, "<label name>");
    r = consumeTokenFast(b, IDENTIFIER);
    if (!r) r = simple_string_lit(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "let" IDENTIFIER "=" Expression
  public static boolean LetClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LET_CLAUSE, "<let clause>");
    r = consumeTokenFast(b, "let");
    r = r && consumeTokens(b, 0, IDENTIFIER, EQ);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" [ ElementList [ "," [ Ellipsis ] ] [ "," ] ] "]"
  public static boolean ListLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit")) return false;
    if (!nextTokenIsFast(b, LEFT_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_BRACKET);
    r = r && ListLit_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, LIST_LIT, r);
    return r;
  }

  // [ ElementList [ "," [ Ellipsis ] ] [ "," ] ]
  private static boolean ListLit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1")) return false;
    ListLit_1_0(b, l + 1);
    return true;
  }

  // ElementList [ "," [ Ellipsis ] ] [ "," ]
  private static boolean ListLit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElementList(b, l + 1);
    r = r && ListLit_1_0_1(b, l + 1);
    r = r && ListLit_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "," [ Ellipsis ] ]
  private static boolean ListLit_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1_0_1")) return false;
    ListLit_1_0_1_0(b, l + 1);
    return true;
  }

  // "," [ Ellipsis ]
  private static boolean ListLit_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COMMA);
    r = r && ListLit_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ Ellipsis ]
  private static boolean ListLit_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1_0_1_0_1")) return false;
    Ellipsis(b, l + 1);
    return true;
  }

  // [ "," ]
  private static boolean ListLit_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_1_0_2")) return false;
    consumeTokenFast(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // BasicLit | ListLit | StructLit
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = BasicLit(b, l + 1);
    if (!r) r = ListLit(b, l + 1);
    if (!r) r = StructLit(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Literal | OperandName | "(" Expression ")"
  public static boolean Operand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERAND, "<operand>");
    r = Literal(b, l + 1);
    if (!r) r = OperandName(b, l + 1);
    if (!r) r = Operand_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "(" Expression ")"
  private static boolean Operand_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operand_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | QualifiedIdent
  public static boolean OperandName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OperandName")) return false;
    if (!nextTokenIsFast(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, IDENTIFIER);
    if (!r) r = QualifiedIdent(b, l + 1);
    exit_section_(b, m, OPERAND_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // "package" PackageName
  public static boolean PackageClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PACKAGE_CLAUSE, "<package clause>");
    r = consumeTokenFast(b, "package");
    r = r && PackageName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean PackageName(PsiBuilder b, int l) {
    return consumeTokenFast(b, IDENTIFIER);
  }

  /* ********************************************************** */
  // Operand {Selector | Index | Slice | Arguments}*
  public static boolean PrimaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPR, "<primary expr>");
    r = Operand(b, l + 1);
    r = r && PrimaryExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // {Selector | Index | Slice | Arguments}*
  private static boolean PrimaryExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrimaryExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrimaryExpr_1", c)) break;
    }
    return true;
  }

  // Selector | Index | Slice | Arguments
  private static boolean PrimaryExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_1_0")) return false;
    boolean r;
    r = Selector(b, l + 1);
    if (!r) r = Index(b, l + 1);
    if (!r) r = consumeTokenFast(b, SLICE);
    if (!r) r = Arguments(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // PackageName "." IDENTIFIER
  public static boolean QualifiedIdent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdent")) return false;
    if (!nextTokenIsFast(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PackageName(b, l + 1);
    r = r && consumeToken(b, ".");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, QUALIFIED_IDENT, r);
    return r;
  }

  /* ********************************************************** */
  // "." (IDENTIFIER | simple_string_lit)
  public static boolean Selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Selector")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECTOR, "<selector>");
    r = consumeTokenFast(b, ".");
    r = r && Selector_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IDENTIFIER | simple_string_lit
  private static boolean Selector_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Selector_1")) return false;
    boolean r;
    r = consumeTokenFast(b, IDENTIFIER);
    if (!r) r = simple_string_lit(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ForClause | GuardClause
  public static boolean StartClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StartClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, START_CLAUSE, "<start clause>");
    r = ForClause(b, l + 1);
    if (!r) r = GuardClause(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "{" { Declaration "," }* "}"
  public static boolean StructLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit")) return false;
    if (!nextTokenIsFast(b, LEFT_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_CURLY);
    r = r && StructLit_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, m, STRUCT_LIT, r);
    return r;
  }

  // { Declaration "," }*
  private static boolean StructLit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructLit_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructLit_1", c)) break;
    }
    return true;
  }

  // Declaration ","
  private static boolean StructLit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Declaration(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "+" | "-"
  static boolean add_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_op")) return false;
    boolean r;
    r = consumeTokenFast(b, "+");
    if (!r) r = consumeTokenFast(b, "-");
    return r;
  }

  /* ********************************************************** */
  // { <<attr_token>> // fixme psi element for attr_token?
  //                       | "(" attr_tokens ")"
  //                       | "[" attr_tokens "]"
  //                       | "{" attr_tokens "}"
  //                     }*
  public static boolean attr_tokens(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_tokens")) return false;
    Marker m = enter_section_(b, l, _COLLAPSE_, ATTR_TOKENS, "<attr tokens>");
    while (true) {
      int c = current_position_(b);
      if (!attr_tokens_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attr_tokens", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // <<attr_token>> // fixme psi element for attr_token?
  //                       | "(" attr_tokens ")"
  //                       | "[" attr_tokens "]"
  //                       | "{" attr_tokens "}"
  private static boolean attr_tokens_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_tokens_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attr_token(b, l + 1);
    if (!r) r = attr_tokens_0_1(b, l + 1);
    if (!r) r = attr_tokens_0_2(b, l + 1);
    if (!r) r = attr_tokens_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" attr_tokens ")"
  private static boolean attr_tokens_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_tokens_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && attr_tokens(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" attr_tokens "]"
  private static boolean attr_tokens_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_tokens_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_BRACKET);
    r = r && attr_tokens(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // "{" attr_tokens "}"
  private static boolean attr_tokens_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_tokens_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_CURLY);
    r = r && attr_tokens(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "@" IDENTIFIER "(" attr_tokens ")"
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIsFast(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, IDENTIFIER, LEFT_PAREN);
    r = r && attr_tokens(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // "|" | "&" | "||" | "&&" | "==" | rel_op | add_op | mul_op
  static boolean binary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_op")) return false;
    boolean r;
    r = consumeTokenFast(b, "|");
    if (!r) r = consumeTokenFast(b, "&");
    if (!r) r = consumeTokenFast(b, "||");
    if (!r) r = consumeTokenFast(b, "&&");
    if (!r) r = consumeTokenFast(b, "==");
    if (!r) r = rel_op(b, l + 1);
    if (!r) r = add_op(b, l + 1);
    if (!r) r = mul_op(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // [ PackageClause "," ]  { ImportDecl "," }* { Declaration "," }*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = file_0(b, l + 1);
    r = r && file_1(b, l + 1);
    r = r && file_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ PackageClause "," ]
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    file_0_0(b, l + 1);
    return true;
  }

  // PackageClause ","
  private static boolean file_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PackageClause(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // { ImportDecl "," }*
  private static boolean file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_1", c)) break;
    }
    return true;
  }

  // ImportDecl ","
  private static boolean file_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDecl(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // { Declaration "," }*
  private static boolean file_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_2", c)) break;
    }
    return true;
  }

  // Declaration ","
  private static boolean file_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Declaration(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INTERPOLATION_END Expression INTERPOLATION_END
  public static boolean interpolation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation")) return false;
    if (!nextTokenIsFast(b, INTERPOLATION_END)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, INTERPOLATION_END);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, INTERPOLATION_END);
    exit_section_(b, m, INTERPOLATION, r);
    return r;
  }

  /* ********************************************************** */
  // "*" | "/" | "div" | "mod" | "quo" | "rem"
  static boolean mul_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mul_op")) return false;
    boolean r;
    r = consumeTokenFast(b, "*");
    if (!r) r = consumeTokenFast(b, "/");
    if (!r) r = consumeTokenFast(b, "div");
    if (!r) r = consumeTokenFast(b, "mod");
    if (!r) r = consumeTokenFast(b, "quo");
    if (!r) r = consumeTokenFast(b, "rem");
    return r;
  }

  /* ********************************************************** */
  // MULTILINE_BYTES_START NEWLINE { UNICODE_VALUE | BYTE_VALUE | interpolation | NEWLINE }* NEWLINE* MULTILINE_BYTES_END
  public static boolean multiline_bytes_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit")) return false;
    if (!nextTokenIsFast(b, MULTILINE_BYTES_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MULTILINE_BYTES_START, NEWLINE);
    r = r && multiline_bytes_lit_2(b, l + 1);
    r = r && multiline_bytes_lit_3(b, l + 1);
    r = r && consumeToken(b, MULTILINE_BYTES_END);
    exit_section_(b, m, MULTILINE_BYTES_LIT, r);
    return r;
  }

  // { UNICODE_VALUE | BYTE_VALUE | interpolation | NEWLINE }*
  private static boolean multiline_bytes_lit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!multiline_bytes_lit_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiline_bytes_lit_2", c)) break;
    }
    return true;
  }

  // UNICODE_VALUE | BYTE_VALUE | interpolation | NEWLINE
  private static boolean multiline_bytes_lit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit_2_0")) return false;
    boolean r;
    r = consumeTokenFast(b, UNICODE_VALUE);
    if (!r) r = consumeTokenFast(b, BYTE_VALUE);
    if (!r) r = interpolation(b, l + 1);
    if (!r) r = consumeTokenFast(b, NEWLINE);
    return r;
  }

  // NEWLINE*
  private static boolean multiline_bytes_lit_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeTokenFast(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "multiline_bytes_lit_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // MULTILINE_STRING_START NEWLINE { UNICODE_VALUE | interpolation | NEWLINE }* NEWLINE* MULTILINE_STRING_END
  public static boolean multiline_string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit")) return false;
    if (!nextTokenIsFast(b, MULTILINE_STRING_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MULTILINE_STRING_START, NEWLINE);
    r = r && multiline_string_lit_2(b, l + 1);
    r = r && multiline_string_lit_3(b, l + 1);
    r = r && consumeToken(b, MULTILINE_STRING_END);
    exit_section_(b, m, MULTILINE_STRING_LIT, r);
    return r;
  }

  // { UNICODE_VALUE | interpolation | NEWLINE }*
  private static boolean multiline_string_lit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!multiline_string_lit_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiline_string_lit_2", c)) break;
    }
    return true;
  }

  // UNICODE_VALUE | interpolation | NEWLINE
  private static boolean multiline_string_lit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit_2_0")) return false;
    boolean r;
    r = consumeTokenFast(b, UNICODE_VALUE);
    if (!r) r = interpolation(b, l + 1);
    if (!r) r = consumeTokenFast(b, NEWLINE);
    return r;
  }

  // NEWLINE*
  private static boolean multiline_string_lit_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeTokenFast(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "multiline_string_lit_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "!=" | "<" | "<=" | ">" | ">=" | "=~" | "!~"
  static boolean rel_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rel_op")) return false;
    boolean r;
    r = consumeTokenFast(b, "!=");
    if (!r) r = consumeTokenFast(b, "<");
    if (!r) r = consumeTokenFast(b, "<=");
    if (!r) r = consumeTokenFast(b, ">");
    if (!r) r = consumeTokenFast(b, ">=");
    if (!r) r = consumeTokenFast(b, "=~");
    if (!r) r = consumeTokenFast(b, "!~");
    return r;
  }

  /* ********************************************************** */
  // SINGLE_QUOTE { UNICODE_VALUE | interpolation }* SINGLE_QUOTE_END
  public static boolean simple_bytes_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit")) return false;
    if (!nextTokenIsFast(b, SINGLE_QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, SINGLE_QUOTE);
    r = r && simple_bytes_lit_1(b, l + 1);
    r = r && consumeToken(b, SINGLE_QUOTE_END);
    exit_section_(b, m, SIMPLE_BYTES_LIT, r);
    return r;
  }

  // { UNICODE_VALUE | interpolation }*
  private static boolean simple_bytes_lit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!simple_bytes_lit_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_bytes_lit_1", c)) break;
    }
    return true;
  }

  // UNICODE_VALUE | interpolation
  private static boolean simple_bytes_lit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit_1_0")) return false;
    boolean r;
    r = consumeTokenFast(b, UNICODE_VALUE);
    if (!r) r = interpolation(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTE { UNICODE_VALUE | interpolation }* DOUBLE_QUOTE_END
  public static boolean simple_string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit")) return false;
    if (!nextTokenIsFast(b, DOUBLE_QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, DOUBLE_QUOTE);
    r = r && simple_string_lit_1(b, l + 1);
    r = r && consumeToken(b, DOUBLE_QUOTE_END);
    exit_section_(b, m, SIMPLE_STRING_LIT, r);
    return r;
  }

  // { UNICODE_VALUE | interpolation }*
  private static boolean simple_string_lit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!simple_string_lit_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_string_lit_1", c)) break;
    }
    return true;
  }

  // UNICODE_VALUE | interpolation
  private static boolean simple_string_lit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit_1_0")) return false;
    boolean r;
    r = consumeTokenFast(b, UNICODE_VALUE);
    if (!r) r = interpolation(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // simple_string_lit |
  //                    multiline_string_lit |
  //                    simple_bytes_lit |
  //                    multiline_bytes_lit |
  //                    "#" string_lit "#"
  public static boolean string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_lit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LIT, "<string lit>");
    r = simple_string_lit(b, l + 1);
    if (!r) r = multiline_string_lit(b, l + 1);
    if (!r) r = simple_bytes_lit(b, l + 1);
    if (!r) r = multiline_bytes_lit(b, l + 1);
    if (!r) r = string_lit_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "#" string_lit "#"
  private static boolean string_lit_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_lit_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, "#");
    r = r && string_lit(b, l + 1);
    r = r && consumeToken(b, "#");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "+" | "-" | "!" | "*" | rel_op
  static boolean unary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_op")) return false;
    boolean r;
    r = consumeTokenFast(b, "+");
    if (!r) r = consumeTokenFast(b, "-");
    if (!r) r = consumeTokenFast(b, "!");
    if (!r) r = consumeTokenFast(b, "*");
    if (!r) r = rel_op(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: ATOM(UnaryExpr)
  // 1: BINARY(BinaryExpr)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = UnaryExpr(b, l + 1);
    p = r;
    r = r && Expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 1 && binary_op(b, l + 1)) {
        r = Expression(b, l, 1);
        exit_section_(b, l, m, BINARY_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // PrimaryExpr | unary_op UnaryExpr
  public static boolean UnaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_EXPR, "<unary expr>");
    r = PrimaryExpr(b, l + 1);
    if (!r) r = UnaryExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // unary_op UnaryExpr
  private static boolean UnaryExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unary_op(b, l + 1);
    r = r && UnaryExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
