package dev.monogon.cue.lang.editor.formatter;

import com.intellij.testFramework.FileBasedTestCaseHelperEx;
import dev.monogon.cue.CueParameterizedLightTest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

public class CueLineIndentProviderTest extends CueParameterizedLightTest implements FileBasedTestCaseHelperEx {
    @Test
    public void lineIndent() {
        myFixture.configureByFile(testFileName);
        myFixture.type('\n');
        myFixture.checkResultByFile(getTestFileNameAfter());
    }

    @Override
    public @NotNull String getRelativeBasePath() {
        return "lang/cue/lineIndent";
    }

    @Override
    public @Nullable String getFileSuffix(@NotNull String fileName) {
        return null;
    }
}