package dev.monogon.cue.lang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LexerTestCase;
import dev.monogon.cue.CueTests;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(Parameterized.class)
public class CueCommaInsertingLexerTest extends LexerTestCase {
    private final String filePath;

    public CueCommaInsertingLexerTest(@NotNull String filePath) {
        this.filePath = filePath;
    }

    @Test
    public void lexer() {
        doFileTest("cue");
    }

    @Override
    protected @NotNull Lexer createLexer() {
        return new CueCommaInsertingLexer();
    }

    @Override
    protected @NotNull String getDirPath() {
        return rootPath().toString();
    }

    @Override
    protected @NotNull String getPathToTestDataFile(@NotNull String extension) {
        return Paths.get(getDirPath(), filePath.replace(".cue", extension)).toString();
    }

    @Parameters(name = "{0}")
    public static Iterable<String> files() {
        return CueTests.findTestFiles(rootPath());
    }

    private static @NotNull Path rootPath() {
        return CueTests.findTestDataPath("lang", "cue", "inserting_lexer");
    }
}