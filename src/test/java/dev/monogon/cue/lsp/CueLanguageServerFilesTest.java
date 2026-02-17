package dev.monogon.cue.lsp;

import dev.monogon.cue.CueLightTest;
import org.junit.Test;

import java.util.Set;

public class CueLanguageServerFilesTest extends CueLightTest {
    @Test
    public void cueSupportedFiles() {
        var cueFile = myFixture.createFile("file.cue", "");
        assertTrue(CueLanguageServerFiles.isCueFile(cueFile));
        assertTrue(CueLanguageServerFiles.isSupportedByCue(getProject(), cueFile));

        for (var ext : Set.of("json", "yml", "yaml")) {
            var file = myFixture.createFile("file." + ext, "");
            assertFalse(CueLanguageServerFiles.isCueFile(file));
            assertTrue(CueLanguageServerFiles.isSupportedByCue(getProject(), file));
        }

        var otherFile = myFixture.createFile("file.txt", "");
        assertFalse(CueLanguageServerFiles.isCueFile(otherFile));
        assertFalse(CueLanguageServerFiles.isSupportedByCue(getProject(), otherFile));
    }
}