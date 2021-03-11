package dev.monogon.cue.lang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import dev.monogon.cue.lang.CueTokenTypes;
import dev.monogon.cue.lang.CueTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Lexer, which automatically inserts zero-length COMMA tokens into the token stream.
 * CUE's grammar requires COMMA tokens and defined rules, when COMMA are automatically inserted.
 * To remain close to the grammar we're using this lexer-based approach.
 * <p>
 * https://cuelang.org/docs/references/spec/#commas
 */
public class CueCommaInsertingLexer extends LookAheadLexer {
    private static final TokenSet VALID_TOKENS = TokenSet.orSet(
        CueTokenTypes.IDENTIFIERS,
        TokenSet.create(
            // KEYWORD is remapped to IDENTIFIER in structs
            CueTypes.KEYWORD,
            CueTypes.INT_LIT, CueTypes.FLOAT_LIT, CueTypes.BOOL_LIT, CueTypes.NULL_LIT, CueTypes.BOTTOM_LIT,
            // end tokens of string literals
            CueTypes.SINGLE_QUOTE_END, CueTypes.DOUBLE_QUOTE_END, CueTypes.MULTILINE_STRING_END, CueTypes.MULTILINE_BYTES_END,
            // fixme string_lit may end with '#', too
            CueTypes.RIGHT_PAREN, CueTypes.RIGHT_BRACKET, CueTypes.RIGHT_CURLY
        ));

    public CueCommaInsertingLexer() {
        super(new CueMergingLexer());
    }

    /**
     * Grammar spec:
     * When the input is broken into tokens, a comma is automatically inserted into the token stream
     * immediately after a line’s final token if that token is
     * - an identifier
     * - null, true, false, bottom, or an integer, floating-point, or string literal
     * - one of the characters ), ], or }
     *
     * @param baseLexer Our flex lexer
     */
    @Override
    protected void lookAhead(@NotNull Lexer baseLexer) {
        IElementType type = baseLexer.getTokenType();
        advanceAs(baseLexer, type);
        if (!VALID_TOKENS.contains(type)) {
            return;
        }

        // insert a zero-length COMMA token between end of current token and the next token (i.e. a linefeed or a comment)
        skipWhitespace(baseLexer);
        IElementType next = baseLexer.getTokenType();
        if (VALID_TOKENS.contains(next)) {
            // skip, if the first accepted EOL candidate is followed by another EOL candidate
            return;
        }

        if (next == null || next == CueTokenTypes.WHITE_SPACE_NEWLINE || next == CueTokenTypes.COMMENT) {
            addToken(baseLexer.getTokenStart(), CueTokenTypes.COMMA_IMPLICIT);
        }

        advanceAs(baseLexer, next);
    }

    private void skipWhitespace(@NotNull Lexer baseLexer) {
        IElementType e = baseLexer.getTokenType();
        // skip whitespace, which isn't a newline
        while (e != null && e == TokenType.WHITE_SPACE) {
            advanceAs(baseLexer, e);
            e = baseLexer.getTokenType();
        }
    }
}
