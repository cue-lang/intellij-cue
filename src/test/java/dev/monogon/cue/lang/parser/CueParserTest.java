package dev.monogon.cue.lang.parser;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.testFramework.ParsingTestCase;
import dev.monogon.cue.CueTests;
import dev.monogon.cue.lang.CueParserDefinition;
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