package dev.monogon.cue.lang.highlighter;

import com.intellij.testFramework.FileBasedTestCaseHelperEx;
import dev.monogon.cue.CueParameterizedLightTest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

/**
 * works on all files in src/test/data/psi/highlighting
 */
public class CueSyntaxHighlighterTest extends CueParameterizedLightTest implements FileBasedTestCaseHelperEx {
    @Test
    public void highlighting() {
        myFixture.testHighlighting(true, true, true, testFileName);
    }

    @Override
    public @NotNull String getRelativeBasePath() {
        return "psi/highlighting";
    }

    @Override
    public @Nullable String getFileSuffix(@NotNull String fileName) {
        return null;
    }
}