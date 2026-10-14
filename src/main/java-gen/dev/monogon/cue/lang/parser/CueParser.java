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
    create_token_set_(DECLARATION, EMBEDDING, FIELD),
    create_token_set_(ALIAS_EXPR, ARGUMENT, ARGUMENTS, BASIC_LIT,
      BINARY_EXPR, EXPRESSION, FUNC, INDEX,
      LIST_LIT, LITERAL, MULTILINE_BYTES_LIT, MULTILINE_STRING_LIT,
      OPERAND, OPERAND_NAME, PRIMARY_EXPR, QUALIFIED_IDENT,
      SELECTOR, SIMPLE_BYTES_LIT, SIMPLE_STRING_LIT, SPREAD,
      STRUCT_LIT, UNARY_EXPR, UNIFICATION_BINARY_EXPR),
  };

  /* ********************************************************** */
  // [ <<identifier_ref>> "=" ] Expression
  public static boolean AliasExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ALIAS_EXPR, "<alias expr>");
    r = AliasExpr_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ <<identifier_ref>> "=" ]
  private static boolean AliasExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasExpr_0")) return false;
    AliasExpr_0_0(b, l + 1);
    return true;
  }

  // <<identifier_ref>> "="
  private static boolean AliasExpr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasExpr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier_ref(b, l + 1);
    r = r && consumeToken(b, EQ);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [ <<identifier_ref>> ":" ] Expression
  public static boolean Argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ARGUMENT, "<argument>");
    r = Argument_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ <<identifier_ref>> ":" ]
  private static boolean Argument_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument_0")) return false;
    Argument_0_0(b, l + 1);
    return true;
  }

  // <<identifier_ref>> ":"
  private static boolean Argument_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier_ref(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(" [ ( Argument { comma Argument }* ) [ comma ] ] [ "..." [ comma ] ] ")"
  public static boolean Arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments")) return false;
    if (!nextTokenIsFast(b, LEFT_PAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENTS, null);
    r = consumeTokenFast(b, LEFT_PAREN);
    p = r; // pin = 1
    r = r && report_error_(b, Arguments_1(b, l + 1));
    r = p && report_error_(b, Arguments_2(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ ( Argument { comma Argument }* ) [ comma ] ]
  private static boolean Arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1")) return false;
    Arguments_1_0(b, l + 1);
    return true;
  }

  // ( Argument { comma Argument }* ) [ comma ]
  private static boolean Arguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Arguments_1_0_0(b, l + 1);
    r = r && Arguments_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Argument { comma Argument }*
  private static boolean Arguments_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Argument(b, l + 1);
    r = r && Arguments_1_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { comma Argument }*
  private static boolean Arguments_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Arguments_1_0_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Arguments_1_0_0_1", c)) break;
    }
    return true;
  }

  // comma Argument
  private static boolean Arguments_1_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && Argument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ comma ]
  private static boolean Arguments_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0_1")) return false;
    comma(b, l + 1);
    return true;
  }

  // [ "..." [ comma ] ]
  private static boolean Arguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_2")) return false;
    Arguments_2_0(b, l + 1);
    return true;
  }

  // "..." [ comma ]
  private static boolean Arguments_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, ELLIPSIS_TOKEN);
    r = r && Arguments_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ comma ]
  private static boolean Arguments_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_2_0_1")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // INT_LIT | FLOAT_LIT | string_lit | NULL_LIT | BOOL_LIT | BOTTOM_LIT
  public static boolean BasicLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BasicLit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BASIC_LIT, "<basic lit>");
    r = consumeTokenFast(b, INT_LIT);
    if (!r) r = consumeTokenFast(b, FLOAT_LIT);
    if (!r) r = string_lit(b, l + 1);
    if (!r) r = consumeTokenFast(b, NULL_LIT);
    if (!r) r = consumeTokenFast(b, BOOL_LIT);
    if (!r) r = consumeTokenFast(b, BOTTOM_LIT);
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
  // !(<<kw "try">> comma) StartClause { [ comma ] Clause }*
  public static boolean Clauses(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLAUSES, "<clauses>");
    r = Clauses_0(b, l + 1);
    r = r && StartClause(b, l + 1);
    r = r && Clauses_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // !(<<kw "try">> comma)
  private static boolean Clauses_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !Clauses_0_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // <<kw "try">> comma
  private static boolean Clauses_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = kw(b, l + 1, "try");
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { [ comma ] Clause }*
  private static boolean Clauses_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Clauses_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Clauses_2", c)) break;
    }
    return true;
  }

  // [ comma ] Clause
  private static boolean Clauses_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Clauses_2_0_0(b, l + 1);
    r = r && Clause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ comma ]
  private static boolean Clauses_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clauses_2_0_0")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Clauses [ comma ] StructLit [ FallbackClause ]
  public static boolean Comprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comprehension")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPREHENSION, "<comprehension>");
    r = Clauses(b, l + 1);
    r = r && Comprehension_1(b, l + 1);
    r = r && StructLit(b, l + 1);
    r = r && Comprehension_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ comma ]
  private static boolean Comprehension_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comprehension_1")) return false;
    comma(b, l + 1);
    return true;
  }

  // [ FallbackClause ]
  private static boolean Comprehension_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comprehension_3")) return false;
    FallbackClause(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Field | Ellipsis | LetClause | Embedding | attribute
  public static boolean Declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, DECLARATION, "<declaration>");
    r = Field(b, l + 1);
    if (!r) r = Ellipsis(b, l + 1);
    if (!r) r = LetClause(b, l + 1);
    if (!r) r = Embedding(b, l + 1);
    if (!r) r = attribute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Embedding { comma Embedding }*
  public static boolean ElementList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_LIST, "<element list>");
    r = Embedding(b, l + 1);
    r = r && ElementList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // { comma Embedding }*
  private static boolean ElementList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ElementList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ElementList_1", c)) break;
    }
    return true;
  }

  // comma Embedding
  private static boolean ElementList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
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
  // ( <<kw "else">> | <<kw "otherwise">> | <<kw "fallback">> ) ( StructLit | <<expected "'{'">> )
  public static boolean FallbackClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FallbackClause")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FALLBACK_CLAUSE, "<fallback clause>");
    r = FallbackClause_0(b, l + 1);
    p = r; // pin = 1
    r = r && FallbackClause_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // <<kw "else">> | <<kw "otherwise">> | <<kw "fallback">>
  private static boolean FallbackClause_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FallbackClause_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = kw(b, l + 1, "else");
    if (!r) r = kw(b, l + 1, "otherwise");
    if (!r) r = kw(b, l + 1, "fallback");
    exit_section_(b, m, null, r);
    return r;
  }

  // StructLit | <<expected "'{'">>
  private static boolean FallbackClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FallbackClause_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructLit(b, l + 1);
    if (!r) r = expected(b, l + 1, "'{'");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Label ":" { Label ":" }* AliasExpr attribute*
  public static boolean Field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = Label(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Field_2(b, l + 1);
    r = r && AliasExpr(b, l + 1);
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

  // attribute*
  private static boolean Field_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Field_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Field_4", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // <<kw "for">> <<identifier_ref>> [ comma <<identifier_ref>> ] "in" Expression
  public static boolean ForClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_CLAUSE, "<for clause>");
    r = kw(b, l + 1, "for");
    r = r && identifier_ref(b, l + 1);
    r = r && ForClause_2(b, l + 1);
    r = r && consumeToken(b, "in");
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ comma <<identifier_ref>> ]
  private static boolean ForClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2")) return false;
    ForClause_2_0(b, l + 1);
    return true;
  }

  // comma <<identifier_ref>>
  private static boolean ForClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && identifier_ref(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<kw "func">> FuncParams [ "->" Expression [ ":" Expression ] | ":" Expression ]
  public static boolean Func(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC, "<func>");
    r = kw(b, l + 1, "func");
    r = r && FuncParams(b, l + 1);
    r = r && Func_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ "->" Expression [ ":" Expression ] | ":" Expression ]
  private static boolean Func_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2")) return false;
    Func_2_0(b, l + 1);
    return true;
  }

  // "->" Expression [ ":" Expression ] | ":" Expression
  private static boolean Func_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Func_2_0_0(b, l + 1);
    if (!r) r = Func_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "->" Expression [ ":" Expression ]
  private static boolean Func_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, ARROW);
    r = r && Expression(b, l + 1, -1);
    r = r && Func_2_0_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ":" Expression ]
  private static boolean Func_2_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2_0_0_2")) return false;
    Func_2_0_0_2_0(b, l + 1);
    return true;
  }

  // ":" Expression
  private static boolean Func_2_0_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2_0_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COLON);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ":" Expression
  private static boolean Func_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Func_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COLON);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [ <<identifier_ref>> [ PostfixAlias ] [ "?" | "!" ] ":" ] Expression [ "=" Expression ] attribute*
  public static boolean FuncParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_PARAM, "<func param>");
    r = FuncParam_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    r = r && FuncParam_2(b, l + 1);
    r = r && FuncParam_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ <<identifier_ref>> [ PostfixAlias ] [ "?" | "!" ] ":" ]
  private static boolean FuncParam_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_0")) return false;
    FuncParam_0_0(b, l + 1);
    return true;
  }

  // <<identifier_ref>> [ PostfixAlias ] [ "?" | "!" ] ":"
  private static boolean FuncParam_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier_ref(b, l + 1);
    r = r && FuncParam_0_0_1(b, l + 1);
    r = r && FuncParam_0_0_2(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ PostfixAlias ]
  private static boolean FuncParam_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_0_0_1")) return false;
    PostfixAlias(b, l + 1);
    return true;
  }

  // [ "?" | "!" ]
  private static boolean FuncParam_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_0_0_2")) return false;
    FuncParam_0_0_2_0(b, l + 1);
    return true;
  }

  // "?" | "!"
  private static boolean FuncParam_0_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_0_0_2_0")) return false;
    boolean r;
    r = consumeTokenFast(b, QMARK);
    if (!r) r = consumeTokenFast(b, EXCL);
    return r;
  }

  // [ "=" Expression ]
  private static boolean FuncParam_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_2")) return false;
    FuncParam_2_0(b, l + 1);
    return true;
  }

  // "=" Expression
  private static boolean FuncParam_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, EQ);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attribute*
  private static boolean FuncParam_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParam_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FuncParam_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "(" [ ( FuncParam | "..." ) { comma ( FuncParam | "..." ) }* [ comma ] ] ")"
  public static boolean FuncParams(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams")) return false;
    if (!nextTokenIsFast(b, LEFT_PAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNC_PARAMS, null);
    r = consumeTokenFast(b, LEFT_PAREN);
    p = r; // pin = 1
    r = r && report_error_(b, FuncParams_1(b, l + 1));
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ ( FuncParam | "..." ) { comma ( FuncParam | "..." ) }* [ comma ] ]
  private static boolean FuncParams_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1")) return false;
    FuncParams_1_0(b, l + 1);
    return true;
  }

  // ( FuncParam | "..." ) { comma ( FuncParam | "..." ) }* [ comma ]
  private static boolean FuncParams_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FuncParams_1_0_0(b, l + 1);
    r = r && FuncParams_1_0_1(b, l + 1);
    r = r && FuncParams_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FuncParam | "..."
  private static boolean FuncParams_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0_0")) return false;
    boolean r;
    r = FuncParam(b, l + 1);
    if (!r) r = consumeTokenFast(b, ELLIPSIS_TOKEN);
    return r;
  }

  // { comma ( FuncParam | "..." ) }*
  private static boolean FuncParams_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FuncParams_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FuncParams_1_0_1", c)) break;
    }
    return true;
  }

  // comma ( FuncParam | "..." )
  private static boolean FuncParams_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && FuncParams_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FuncParam | "..."
  private static boolean FuncParams_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0_1_0_1")) return false;
    boolean r;
    r = FuncParam(b, l + 1);
    if (!r) r = consumeTokenFast(b, ELLIPSIS_TOKEN);
    return r;
  }

  // [ comma ]
  private static boolean FuncParams_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncParams_1_0_2")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // <<kw "if">> Expression
  public static boolean GuardClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GuardClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GUARD_CLAUSE, "<guard clause>");
    r = kw(b, l + 1, "if");
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "import" ( ImportSpec | "(" { ImportSpec comma }* ")" )
  public static boolean ImportDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_DECL, "<import decl>");
    r = consumeTokenFast(b, "import");
    p = r; // pin = 1
    r = r && ImportDecl_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ImportSpec | "(" { ImportSpec comma }* ")"
  private static boolean ImportDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    if (!r) r = ImportDecl_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" { ImportSpec comma }* ")"
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

  // { ImportSpec comma }*
  private static boolean ImportDecl_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDecl_1_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportDecl_1_1_1", c)) break;
    }
    return true;
  }

  // ImportSpec comma
  private static boolean ImportDecl_1_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // { unicode_value }*
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

  // { unicode_value }
  private static boolean ImportLocation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportLocation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unicode_value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "\"" ImportLocation [ ":" identifier ] "\""
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

  // [ ":" identifier ]
  private static boolean ImportPath_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_2")) return false;
    ImportPath_2_0(b, l + 1);
    return true;
  }

  // ":" identifier
  private static boolean ImportPath_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COLON);
    r = r && identifier(b, l + 1);
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
  // "[" Expression (":" Expression)? [ comma ] "]"
  public static boolean Index(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Index")) return false;
    if (!nextTokenIsFast(b, LEFT_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INDEX, null);
    r = consumeTokenFast(b, LEFT_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && report_error_(b, Index_2(b, l + 1)) && r;
    r = p && report_error_(b, Index_3(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (":" Expression)?
  private static boolean Index_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Index_2")) return false;
    Index_2_0(b, l + 1);
    return true;
  }

  // ":" Expression
  private static boolean Index_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Index_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, COLON);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ comma ]
  private static boolean Index_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Index_3")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [ identifier "=" ] LabelExpr
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL, "<label>");
    r = Label_0(b, l + 1);
    r = r && LabelExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ identifier "=" ]
  private static boolean Label_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0")) return false;
    Label_0_0(b, l + 1);
    return true;
  }

  // identifier "="
  private static boolean Label_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQ);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LabelName [ PostfixAlias ] ["?" | "!"]
  //                   | "[" AliasExpr "]" [ PostfixAlias ]
  public static boolean LabelExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_EXPR, "<label expr>");
    r = LabelExpr_0(b, l + 1);
    if (!r) r = LabelExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LabelName [ PostfixAlias ] ["?" | "!"]
  private static boolean LabelExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LabelName(b, l + 1);
    r = r && LabelExpr_0_1(b, l + 1);
    r = r && LabelExpr_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ PostfixAlias ]
  private static boolean LabelExpr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0_1")) return false;
    PostfixAlias(b, l + 1);
    return true;
  }

  // ["?" | "!"]
  private static boolean LabelExpr_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0_2")) return false;
    LabelExpr_0_2_0(b, l + 1);
    return true;
  }

  // "?" | "!"
  private static boolean LabelExpr_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_0_2_0")) return false;
    boolean r;
    r = consumeTokenFast(b, QMARK);
    if (!r) r = consumeTokenFast(b, EXCL);
    return r;
  }

  // "[" AliasExpr "]" [ PostfixAlias ]
  private static boolean LabelExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_BRACKET);
    r = r && AliasExpr(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    r = r && LabelExpr_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ PostfixAlias ]
  private static boolean LabelExpr_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelExpr_1_3")) return false;
    PostfixAlias(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // <<struct_label>>
  //                   | simple_string_lit
  //                   | "(" AliasExpr ")"
  public static boolean LabelName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_NAME, "<label name>");
    r = struct_label(b, l + 1);
    if (!r) r = simple_string_lit(b, l + 1);
    if (!r) r = LabelName_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "(" AliasExpr ")"
  private static boolean LabelName_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelName_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && AliasExpr(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<kw "let">> <<identifier_ref>> "=" Expression
  public static boolean LetClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LET_CLAUSE, "<let clause>");
    r = kw(b, l + 1, "let");
    r = r && identifier_ref(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [ HASH ] "[" [(ElementList [comma Ellipsis]) | Ellipsis]  [comma] "]"
  public static boolean ListLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit")) return false;
    if (!nextTokenIsFast(b, HASH, LEFT_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LIST_LIT, "<list lit>");
    r = ListLit_0(b, l + 1);
    r = r && consumeToken(b, LEFT_BRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, ListLit_2(b, l + 1));
    r = p && report_error_(b, ListLit_3(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ HASH ]
  private static boolean ListLit_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_0")) return false;
    consumeTokenFast(b, HASH);
    return true;
  }

  // [(ElementList [comma Ellipsis]) | Ellipsis]
  private static boolean ListLit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_2")) return false;
    ListLit_2_0(b, l + 1);
    return true;
  }

  // (ElementList [comma Ellipsis]) | Ellipsis
  private static boolean ListLit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ListLit_2_0_0(b, l + 1);
    if (!r) r = Ellipsis(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ElementList [comma Ellipsis]
  private static boolean ListLit_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElementList(b, l + 1);
    r = r && ListLit_2_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [comma Ellipsis]
  private static boolean ListLit_2_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_2_0_0_1")) return false;
    ListLit_2_0_0_1_0(b, l + 1);
    return true;
  }

  // comma Ellipsis
  private static boolean ListLit_2_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_2_0_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && Ellipsis(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [comma]
  private static boolean ListLit_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListLit_3")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // BasicLit | ListLit | StructLit
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LITERAL, "<literal>");
    r = BasicLit(b, l + 1);
    if (!r) r = ListLit(b, l + 1);
    if (!r) r = StructLit(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Literal | Func | OperandName | "(" Expression ")"
  public static boolean Operand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OPERAND, "<operand>");
    r = Literal(b, l + 1);
    if (!r) r = Func(b, l + 1);
    if (!r) r = OperandName(b, l + 1);
    if (!r) r = Operand_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "(" Expression ")"
  private static boolean Operand_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operand_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<identifier_ref>> | QualifiedIdent
  public static boolean OperandName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OperandName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OPERAND_NAME, "<operand name>");
    r = identifier_ref(b, l + 1);
    if (!r) r = QualifiedIdent(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // identifier
  static boolean PackageName(PsiBuilder b, int l) {
    return identifier(b, l + 1);
  }

  /* ********************************************************** */
  // "~" ( <<identifier_ref>> | "(" <<identifier_ref>> [ comma <<identifier_ref>> ] ")" | <<expected "<identifier> or '('">> )
  public static boolean PostfixAlias(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixAlias")) return false;
    if (!nextTokenIsFast(b, TILDE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, POSTFIX_ALIAS, null);
    r = consumeTokenFast(b, TILDE);
    p = r; // pin = 1
    r = r && PostfixAlias_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // <<identifier_ref>> | "(" <<identifier_ref>> [ comma <<identifier_ref>> ] ")" | <<expected "<identifier> or '('">>
  private static boolean PostfixAlias_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixAlias_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier_ref(b, l + 1);
    if (!r) r = PostfixAlias_1_1(b, l + 1);
    if (!r) r = expected(b, l + 1, "<identifier> or '('");
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" <<identifier_ref>> [ comma <<identifier_ref>> ] ")"
  private static boolean PostfixAlias_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixAlias_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LEFT_PAREN);
    r = r && identifier_ref(b, l + 1);
    r = r && PostfixAlias_1_1_2(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ comma <<identifier_ref>> ]
  private static boolean PostfixAlias_1_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixAlias_1_1_2")) return false;
    PostfixAlias_1_1_2_0(b, l + 1);
    return true;
  }

  // comma <<identifier_ref>>
  private static boolean PostfixAlias_1_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixAlias_1_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && identifier_ref(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Operand {Selector | Index /*| Slice*/ | Arguments | Spread | "?"}*
  public static boolean PrimaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PRIMARY_EXPR, "<primary expr>");
    r = Operand(b, l + 1);
    r = r && PrimaryExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // {Selector | Index /*| Slice*/ | Arguments | Spread | "?"}*
  private static boolean PrimaryExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrimaryExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrimaryExpr_1", c)) break;
    }
    return true;
  }

  // Selector | Index /*| Slice*/ | Arguments | Spread | "?"
  private static boolean PrimaryExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_1_0")) return false;
    boolean r;
    r = Selector(b, l + 1);
    if (!r) r = Index(b, l + 1);
    if (!r) r = Arguments(b, l + 1);
    if (!r) r = Spread(b, l + 1);
    if (!r) r = consumeTokenFast(b, QMARK);
    return r;
  }

  /* ********************************************************** */
  // PackageName "." <<identifier_ref>>
  public static boolean QualifiedIdent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdent")) return false;
    if (!nextTokenIsFast(b, IDENTIFIER, IDENTIFIER_PREDECLARED)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUALIFIED_IDENT, "<qualified ident>");
    r = PackageName(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && identifier_ref(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "." (<<struct_label>> | simple_string_lit)
  public static boolean Selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Selector")) return false;
    if (!nextTokenIsFast(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, DOT);
    r = r && Selector_1(b, l + 1);
    exit_section_(b, m, SELECTOR, r);
    return r;
  }

  // <<struct_label>> | simple_string_lit
  private static boolean Selector_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Selector_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = struct_label(b, l + 1);
    if (!r) r = simple_string_lit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "..."
  public static boolean Spread(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Spread")) return false;
    if (!nextTokenIsFast(b, ELLIPSIS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, ELLIPSIS_TOKEN);
    exit_section_(b, m, SPREAD, r);
    return r;
  }

  /* ********************************************************** */
  // ForClause | GuardClause | TryClause
  public static boolean StartClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StartClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, START_CLAUSE, "<start clause>");
    r = ForClause(b, l + 1);
    if (!r) r = GuardClause(b, l + 1);
    if (!r) r = TryClause(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [ HASH ] "{" [ Declaration {comma Declaration}* comma? ] "}"
  public static boolean StructLit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit")) return false;
    if (!nextTokenIsFast(b, HASH, LEFT_CURLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_LIT, "<struct lit>");
    r = StructLit_0(b, l + 1);
    r = r && consumeToken(b, LEFT_CURLY);
    p = r; // pin = 2
    r = r && report_error_(b, StructLit_2(b, l + 1));
    r = p && consumeToken(b, RIGHT_CURLY) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ HASH ]
  private static boolean StructLit_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_0")) return false;
    consumeTokenFast(b, HASH);
    return true;
  }

  // [ Declaration {comma Declaration}* comma? ]
  private static boolean StructLit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_2")) return false;
    StructLit_2_0(b, l + 1);
    return true;
  }

  // Declaration {comma Declaration}* comma?
  private static boolean StructLit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Declaration(b, l + 1);
    r = r && StructLit_2_0_1(b, l + 1);
    r = r && StructLit_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // {comma Declaration}*
  private static boolean StructLit_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructLit_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructLit_2_0_1", c)) break;
    }
    return true;
  }

  // comma Declaration
  private static boolean StructLit_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comma(b, l + 1);
    r = r && Declaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comma?
  private static boolean StructLit_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLit_2_0_2")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // <<kw "try">> [ <<identifier_ref>> "=" Expression ]
  public static boolean TryClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TRY_CLAUSE, "<try clause>");
    r = kw(b, l + 1, "try");
    r = r && TryClause_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ <<identifier_ref>> "=" Expression ]
  private static boolean TryClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryClause_1")) return false;
    TryClause_1_0(b, l + 1);
    return true;
  }

  // <<identifier_ref>> "=" Expression
  private static boolean TryClause_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryClause_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier_ref(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ADD_OP
  static boolean add_op(PsiBuilder b, int l) {
    return consumeTokenFast(b, ADD_OP);
  }

  /* ********************************************************** */
  // { <<attr_token>> // fixme remap token type of for attr_token tokens?
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

  // <<attr_token>> // fixme remap token type of for attr_token tokens?
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
  // "@" <<attribute_name>> "(" attr_tokens ")"
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIsFast(b, AT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, null);
    r = consumeTokenFast(b, AT);
    p = r; // pin = 1
    r = r && report_error_(b, attribute_name(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LEFT_PAREN)) && r;
    r = p && report_error_(b, attr_tokens(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // OP_DISJUNCTION | OP_OR | OP_AND | OP_EQ | rel_op | add_op | mul_op
  static boolean binary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_op")) return false;
    boolean r;
    r = consumeTokenFast(b, OP_DISJUNCTION);
    if (!r) r = consumeTokenFast(b, OP_OR);
    if (!r) r = consumeTokenFast(b, OP_AND);
    if (!r) r = consumeTokenFast(b, OP_EQ);
    if (!r) r = rel_op(b, l + 1);
    if (!r) r = add_op(b, l + 1);
    if (!r) r = mul_op(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // <<read_comma>>
  static boolean comma(PsiBuilder b, int l) {
    return read_comma(b, l + 1);
  }

  /* ********************************************************** */
  // { attribute comma }* [ PackageClause comma ] [ { attribute comma }* ImportDecl comma ] { ImportDecl comma }* { Declaration comma }*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = file_0(b, l + 1);
    r = r && file_1(b, l + 1);
    r = r && file_2(b, l + 1);
    r = r && file_3(b, l + 1);
    r = r && file_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { attribute comma }*
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_0", c)) break;
    }
    return true;
  }

  // attribute comma
  private static boolean file_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ PackageClause comma ]
  private static boolean file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1")) return false;
    file_1_0(b, l + 1);
    return true;
  }

  // PackageClause comma
  private static boolean file_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PackageClause(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ { attribute comma }* ImportDecl comma ]
  private static boolean file_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2")) return false;
    file_2_0(b, l + 1);
    return true;
  }

  // { attribute comma }* ImportDecl comma
  private static boolean file_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = file_2_0_0(b, l + 1);
    r = r && ImportDecl(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { attribute comma }*
  private static boolean file_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_2_0_0", c)) break;
    }
    return true;
  }

  // attribute comma
  private static boolean file_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { ImportDecl comma }*
  private static boolean file_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_3", c)) break;
    }
    return true;
  }

  // ImportDecl comma
  private static boolean file_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDecl(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { Declaration comma }*
  private static boolean file_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_4", c)) break;
    }
    return true;
  }

  // Declaration comma
  private static boolean file_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Declaration(b, l + 1);
    r = r && comma(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | IDENTIFIER_PREDECLARED
  static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIsFast(b, IDENTIFIER, IDENTIFIER_PREDECLARED)) return false;
    boolean r;
    r = consumeTokenFast(b, IDENTIFIER);
    if (!r) r = consumeTokenFast(b, IDENTIFIER_PREDECLARED);
    return r;
  }

  /* ********************************************************** */
  // INTERPOLATION_START Expression [ comma ] INTERPOLATION_END
  public static boolean interpolation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation")) return false;
    if (!nextTokenIsFast(b, INTERPOLATION_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INTERPOLATION, null);
    r = consumeTokenFast(b, INTERPOLATION_START);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && report_error_(b, interpolation_2(b, l + 1)) && r;
    r = p && consumeToken(b, INTERPOLATION_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ comma ]
  private static boolean interpolation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation_2")) return false;
    comma(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MUL_OP
  static boolean mul_op(PsiBuilder b, int l) {
    return consumeTokenFast(b, MUL_OP);
  }

  /* ********************************************************** */
  // MULTILINE_BYTES_START NEWLINE { unicode_value | interpolation | BYTE_VALUE | NEWLINE }* NEWLINE* MULTILINE_BYTES_END
  public static boolean multiline_bytes_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit")) return false;
    if (!nextTokenIsFast(b, MULTILINE_BYTES_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MULTILINE_BYTES_LIT, null);
    r = consumeTokens(b, 1, MULTILINE_BYTES_START, NEWLINE);
    p = r; // pin = 1
    r = r && report_error_(b, multiline_bytes_lit_2(b, l + 1));
    r = p && report_error_(b, multiline_bytes_lit_3(b, l + 1)) && r;
    r = p && consumeToken(b, MULTILINE_BYTES_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { unicode_value | interpolation | BYTE_VALUE | NEWLINE }*
  private static boolean multiline_bytes_lit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!multiline_bytes_lit_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiline_bytes_lit_2", c)) break;
    }
    return true;
  }

  // unicode_value | interpolation | BYTE_VALUE | NEWLINE
  private static boolean multiline_bytes_lit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_bytes_lit_2_0")) return false;
    boolean r;
    r = unicode_value(b, l + 1);
    if (!r) r = interpolation(b, l + 1);
    if (!r) r = consumeTokenFast(b, BYTE_VALUE);
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
  // MULTILINE_STRING_START NEWLINE { unicode_value | interpolation | NEWLINE }* NEWLINE* MULTILINE_STRING_END
  public static boolean multiline_string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit")) return false;
    if (!nextTokenIsFast(b, MULTILINE_STRING_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MULTILINE_STRING_LIT, null);
    r = consumeTokens(b, 1, MULTILINE_STRING_START, NEWLINE);
    p = r; // pin = 1
    r = r && report_error_(b, multiline_string_lit_2(b, l + 1));
    r = p && report_error_(b, multiline_string_lit_3(b, l + 1)) && r;
    r = p && consumeToken(b, MULTILINE_STRING_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { unicode_value | interpolation | NEWLINE }*
  private static boolean multiline_string_lit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!multiline_string_lit_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiline_string_lit_2", c)) break;
    }
    return true;
  }

  // unicode_value | interpolation | NEWLINE
  private static boolean multiline_string_lit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiline_string_lit_2_0")) return false;
    boolean r;
    r = unicode_value(b, l + 1);
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
  // REL_OP
  static boolean rel_op(PsiBuilder b, int l) {
    return consumeTokenFast(b, REL_OP);
  }

  /* ********************************************************** */
  // SINGLE_QUOTE { unicode_value | interpolation | BYTE_VALUE }* SINGLE_QUOTE_END
  public static boolean simple_bytes_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit")) return false;
    if (!nextTokenIsFast(b, SINGLE_QUOTE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_BYTES_LIT, null);
    r = consumeTokenFast(b, SINGLE_QUOTE);
    p = r; // pin = 1
    r = r && report_error_(b, simple_bytes_lit_1(b, l + 1));
    r = p && consumeToken(b, SINGLE_QUOTE_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { unicode_value | interpolation | BYTE_VALUE }*
  private static boolean simple_bytes_lit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!simple_bytes_lit_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_bytes_lit_1", c)) break;
    }
    return true;
  }

  // unicode_value | interpolation | BYTE_VALUE
  private static boolean simple_bytes_lit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_bytes_lit_1_0")) return false;
    boolean r;
    r = unicode_value(b, l + 1);
    if (!r) r = interpolation(b, l + 1);
    if (!r) r = consumeTokenFast(b, BYTE_VALUE);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTE { unicode_value | interpolation }* DOUBLE_QUOTE_END
  public static boolean simple_string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit")) return false;
    if (!nextTokenIsFast(b, DOUBLE_QUOTE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_STRING_LIT, null);
    r = consumeTokenFast(b, DOUBLE_QUOTE);
    p = r; // pin = 1
    r = r && report_error_(b, simple_string_lit_1(b, l + 1));
    r = p && consumeToken(b, DOUBLE_QUOTE_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { unicode_value | interpolation }*
  private static boolean simple_string_lit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!simple_string_lit_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "simple_string_lit_1", c)) break;
    }
    return true;
  }

  // unicode_value | interpolation
  private static boolean simple_string_lit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_string_lit_1_0")) return false;
    boolean r;
    r = unicode_value(b, l + 1);
    if (!r) r = interpolation(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // simple_string_lit
  //                    | multiline_string_lit
  //                    | simple_bytes_lit
  //                    | multiline_bytes_lit
  static boolean string_lit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_lit")) return false;
    boolean r;
    r = simple_string_lit(b, l + 1);
    if (!r) r = multiline_string_lit(b, l + 1);
    if (!r) r = simple_bytes_lit(b, l + 1);
    if (!r) r = multiline_bytes_lit(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ADD_OP | EXCL | "*" | OP_EQ | rel_op
  static boolean unary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_op")) return false;
    boolean r;
    r = consumeTokenFast(b, ADD_OP);
    if (!r) r = consumeTokenFast(b, EXCL);
    if (!r) r = consumeTokenFast(b, "*");
    if (!r) r = consumeTokenFast(b, OP_EQ);
    if (!r) r = rel_op(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // UNICODE_VALUE | ESCAPED_CHAR
  static boolean unicode_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unicode_value")) return false;
    if (!nextTokenIsFast(b, ESCAPED_CHAR, UNICODE_VALUE)) return false;
    boolean r;
    r = consumeTokenFast(b, UNICODE_VALUE);
    if (!r) r = consumeTokenFast(b, ESCAPED_CHAR);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: ATOM(UnaryExpr)
  // 1: BINARY(BinaryExpr)
  // 2: BINARY(UnificationBinaryExpr)
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
      else if (g < 2 && consumeTokenSmart(b, OP_UNIFICATION)) {
        r = Expression(b, l, 2);
        exit_section_(b, l, m, UNIFICATION_BINARY_EXPR, r, true, null);
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
