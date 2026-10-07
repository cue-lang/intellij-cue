package dev.monogon.cue.lang.parser;

import com.intellij.lang.LanguageBraceMatching;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.testFramework.ParsingTestCase;
import dev.monogon.cue.CueTests;
import dev.monogon.cue.lang.CueLanguage;
import dev.monogon.cue.lang.CueParserDefinition;
import dev.monogon.cue.lang.editor.CueBraceMatcher;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.file.Path;

/**
 * Run on all files in src/test/data/lang/cue/parser.
 * Error are accepted only for files, which contain _error in the name.
 */
@RunWith(Parameterized.class)
public class CueParserTest extends ParsingTestCase {
    private final String filePath;

    public CueParserTest(@NotNull String filePath) {
        super("", "cue", true, new CueParserDefinition());
        this.filePath = filePath;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Error recovery groups tokens by matching braces when the language has a brace matcher.
        // Register it here, as the IDE does, so that the result does not depend on the tests run before.
        addExplicitExtension(LanguageBraceMatching.INSTANCE, CueLanguage.INSTANCE, new CueBraceMatcher());
    }

    @Test
    public void parser() {
        doTest(true, !getTestName().toLowerCase().contains("error"));
    }

    @Override
    protected String getTestDataPath() {
        return rootPath().toString();
    }

    @Override
    protected @NotNull String getTestName(boolean lowercaseFirstLetter) {
        return StringUtil.trimEnd(filePath, ".cue");
    }

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<String> files() {
        return CueTests.findTestFiles(rootPath());
    }

    private static @NotNull Path rootPath() {
        return CueTests.findTestDataPath("lang", "cue", "parser");
    }
}