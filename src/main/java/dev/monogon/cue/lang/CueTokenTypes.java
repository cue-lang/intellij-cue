package dev.monogon.cue.lang;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface CueTokenTypes {
    // custom token types to create PSI leaf elements
    IElementType COMMA_IMPLICIT = new CueImplicitCommaTokenType();
    TokenSet COMMAS = TokenSet.create(COMMA_IMPLICIT, CueTypes.COMMA);

    // tokenizing newlines, which are whitespace, as separate tokens to simplify out CueCommaInsertingLexer
    IElementType WHITE_SPACE_NEWLINE = new CueTokenType("NEWLINE");
    TokenSet WHITESPACE_TOKENS = TokenSet.create(TokenType.WHITE_SPACE, WHITE_SPACE_NEWLINE);

    TokenSet WHITESPACE_OR_NEWLINE = TokenSet.create(CueTypes.NEWLINE, TokenType.WHITE_SPACE, WHITE_SPACE_NEWLINE);
    TokenSet NEWLINES = TokenSet.create(CueTypes.NEWLINE, WHITE_SPACE_NEWLINE);

    IElementType COMMENT = new CueTokenType("LINE_COMMENT");
    TokenSet COMMENTS = TokenSet.create(COMMENT);

    TokenSet STRING_LITERALS = TokenSet.create(
        CueTypes.SIMPLE_STRING_LIT,
        CueTypes.SIMPLE_BYTES_LIT,
        CueTypes.MULTILINE_STRING_LIT,
        CueTypes.MULTILINE_BYTES_LIT
    );

    TokenSet STRING_SINGLE_OPENING_QUOTES = TokenSet.create(
        CueTypes.DOUBLE_QUOTE,
        CueTypes.SINGLE_QUOTE
    );

    TokenSet LITERALS_OPENING_QUOTES = TokenSet.create(
        CueTypes.DOUBLE_QUOTE,
        CueTypes.SINGLE_QUOTE,
        CueTypes.MULTILINE_STRING_START,
        CueTypes.MULTILINE_BYTES_START
    );

    TokenSet LITERALS_CLOSING_QUOTES = TokenSet.create(
        CueTypes.DOUBLE_QUOTE_END,
        CueTypes.SINGLE_QUOTE_END,
        CueTypes.MULTILINE_STRING_END,
        CueTypes.MULTILINE_BYTES_END
    );

    TokenSet LITERALS_SINGLE_CLOSING_QUOTES = TokenSet.create(
        CueTypes.DOUBLE_QUOTE_END,
        CueTypes.SINGLE_QUOTE_END
    );

    TokenSet OPERATORS = TokenSet.create(
        CueTypes.ADD_OP,
        CueTypes.MUL_OP,
        CueTypes.REL_OP,
        CueTypes.OP_DISJUNCTION,
        CueTypes.OP_UNIFICATION,
        CueTypes.OP_AND,
        CueTypes.OP_OR,
        CueTypes.OP_EQ,
        CueTypes.TILDE,
        CueTypes.ARROW
    );

    TokenSet IDENTIFIERS = TokenSet.create(
        CueTypes.IDENTIFIER,
        CueTypes.IDENTIFIER_PREDECLARED);
}
