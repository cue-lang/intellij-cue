package dev.monogon.cue.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import dev.monogon.cue.lang.CueTokenTypes;
import dev.monogon.cue.lang.CueTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

@SuppressWarnings("DuplicatedCode")
public class CueParserUtil extends GeneratedParserUtilBase {
    private static final TokenSet REJECTED_ATTR_TOKEN = TokenSet.create(
        CueTypes.LEFT_PAREN, CueTypes.RIGHT_PAREN,
        CueTypes.LEFT_BRACKET, CueTypes.RIGHT_BRACKET,
        CueTypes.LEFT_CURLY, CueTypes.RIGHT_CURLY
    );

    /**
     * Records that the given text was expected at the current position and fails,
     * so that an enclosing pinned rule reports an error naming it. The text is a
     * rule name such as {@code <identifier>}, or a token in single quotes, such as
     * {@code '{'}, which the error message quotes itself.
     */
    public static boolean expected(@NotNull PsiBuilder b, @SuppressWarnings("unused") int level, @NotNull String text) {
        if (text.length() > 2 && text.startsWith("'") && text.endsWith("'")) {
            text = text.substring(1, text.length() - 1);
        }
        addVariant(b, text);
        return false;
    }

    /**
     * Reads a keyword token.
     * <p>
     * This is needed because PsiBuilder keeps remapped tokens, even if rules failed to parse later.
     * This has to handle the reverse direction of the methods below.
     * An alternative fix is to move the mapping to the lexer.
     */
    public static boolean kw(@NotNull PsiBuilder b, @SuppressWarnings("unused") int level, @NotNull String name) {
        var type = b.getTokenType();
        if (type == CueTypes.KEYWORD && name.equals(b.getTokenText())) {
            b.advanceLexer();
            return true;
        }
        if (CueTokenTypes.IDENTIFIERS.contains(type) && name.equals(b.getTokenText())) {
            b.remapCurrentToken(CueTypes.KEYWORD);
            b.advanceLexer();
            return true;
        }
        return false;
    }

    // attr_token      = /* any token except '(', ')', '[', ']', '{', or '}' */
    // https://cuelang.org/docs/references/spec/#structs
    public static boolean attr_token(PsiBuilder b, @SuppressWarnings("unused") int level) {
        if (REJECTED_ATTR_TOKEN.contains(b.getTokenType())) {
            return false;
        }
        // fixme do we have to remap the token as ATTR or leave as-is?
        b.advanceLexer();
        return true;
    }

    /**
     * All keywords are allowed as labels/field names and as selectors, and so are null, true and false.
     * These keep their token types, because a remapped token stays remapped if the label is not part of a field.
     * <a href="https://cuelang.org/docs/references/spec/#keywords">...</a>
     */
    public static boolean struct_label(PsiBuilder b, @SuppressWarnings("unused") int level) {
        IElementType type = b.getTokenType();
        if (CueTokenTypes.IDENTIFIERS.contains(type) || type == CueTypes.NULL_LIT || type == CueTypes.BOOL_LIT) {
            b.advanceLexer();
            return true;
        }
        if (type == CueTypes.KEYWORD) {
            b.remapCurrentToken(CueTypes.IDENTIFIER);
            b.advanceLexer();
            return true;
        }
        return false;
    }

    /**
     * Unless noted otherwise, keywords can also be used as identifiers to refer to the same name.
     * <p>
     * "null", "true", "false" can never be used to refer to a field of the same name.
     * This restriction is to ensure compatibility with JSON configuration files.
     * <p>
     * <a href="https://cuelang.org/docs/references/spec/#keywords">...</a>
     */
    public static boolean identifier_ref(PsiBuilder b, @SuppressWarnings("unused") int level) {
        IElementType type = b.getTokenType();
        if (CueTokenTypes.IDENTIFIERS.contains(type)) {
            b.advanceLexer();
            return true;
        }

        // null is a NULL_LIT, true and false are BOOL_LIT, all others are KEYWORD, so we can just remap KEYWORD
        if (type == CueTypes.KEYWORD) {
            b.remapCurrentToken(CueTypes.IDENTIFIER);
            b.advanceLexer();
            return true;
        }
        return false;
    }

    /**
     * Tokens which are allowed as attribute names.
     * null, true, false seem to be allowed in addition to the regular identifier rules.
     */
    public static boolean attribute_name(PsiBuilder b, @SuppressWarnings("unused") int level) {
        IElementType type = b.getTokenType();
        if (CueTokenTypes.IDENTIFIERS.contains(type)) {
            b.advanceLexer();
            return true;
        }

        if (type == CueTypes.KEYWORD || type == CueTypes.NULL_LIT || type == CueTypes.BOOL_LIT) {
            b.remapCurrentToken(CueTypes.IDENTIFIER);
            b.advanceLexer();
            return true;
        }
        return false;
    }

    /**
     * Reads a field label, as {@link #struct_label} does, and reports an error on
     * one that starts with "__", which CUE reserves.
     */
    public static boolean label_decl(PsiBuilder b, int level) {
        return checkReserved(b, () -> struct_label(b, level));
    }

    /**
     * Reads a declared name, as {@link #identifier_ref} does, and reports an error
     * on one that starts with "__", which CUE reserves.
     */
    public static boolean identifier_ref_decl(PsiBuilder b, int level) {
        return checkReserved(b, () -> identifier_ref(b, level));
    }

    /**
     * Reads a declared name that must be an identifier, and reports an error on
     * one that starts with "__", which CUE reserves. The lexer reads such a name
     * as a keyword, which is kept, so that nothing is remapped if the enclosing
     * rule is discarded.
     */
    public static boolean identifier_decl(PsiBuilder b, @SuppressWarnings("unused") int level) {
        return checkReserved(b, () -> {
            var type = b.getTokenType();
            if (CueTokenTypes.IDENTIFIERS.contains(type) || type == CueTypes.KEYWORD && isReserved(b.getTokenText())) {
                b.advanceLexer();
                return true;
            }
            return false;
        });
    }

    /**
     * Reads a name with the given reader and, if it starts with "__", reports an
     * error on it, as CUE's parser does for a declared name. The name stays read.
     */
    private static boolean checkReserved(PsiBuilder b, BooleanSupplier reader) {
        var text = b.getTokenText();
        var marker = b.mark();
        if (!reader.getAsBoolean()) {
            marker.drop();
            return false;
        }
        if (isReserved(text)) {
            marker.error("identifiers starting with '__' are reserved");
        }
        else {
            marker.drop();
        }
        return true;
    }

    /** Reports whether the given name is one that CUE reserves, starting with "__". */
    private static boolean isReserved(@Nullable String name) {
        return name != null && name.startsWith("__");
    }

    /**
     * Reads an ellipsis of a closed list with the given parser, and reports an
     * error on it, as CUE's parser does. The ellipsis stays read.
     */
    public static boolean closed_list_ellipsis(PsiBuilder b, int level, Parser ellipsis) {
        var marker = b.mark();
        if (!ellipsis.parse(b, level)) {
            marker.drop();
            return false;
        }
        marker.error("closed list literal cannot end in an ellipsis");
        return true;
    }

    public static boolean read_comma(PsiBuilder b, @SuppressWarnings("unused") int level) {
        var type = b.getTokenType();
        if (type == CueTypes.COMMA || type == CueTokenTypes.COMMA_IMPLICIT) {
            b.advanceLexer();
            return true;
        }
        return false;
    }
}