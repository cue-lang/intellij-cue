package dev.monogon.cue.lsp;

import dev.monogon.cue.CueLightTest;
import org.junit.Test;

public class CueLanguageServerFilesTest extends CueLightTest {
    @Test
    public void cueSupportedFiles() {
        var cueFile = myFixture.createFile("file.cue", "");
        var jsonFile = myFixture.createFile("file.json", "");
        var yamlFile = myFixture.createFile("file.yaml", "");
        var otherFile = myFixture.createFile("file.txt", "");

        assertTrue(CueLanguageServerFiles.isCueFile(cueFile));
        assertTrue(CueLanguageServerFiles.isSupportedByCue(cueFile));

        assertFalse(CueLanguageServerFiles.isCueFile(jsonFile));
        assertTrue(CueLanguageServerFiles.isSupportedByCue(jsonFile));

        assertFalse(CueLanguageServerFiles.isCueFile(yamlFile));
        assertTrue(CueLanguageServerFiles.isSupportedByCue(yamlFile));

        assertFalse(CueLanguageServerFiles.isCueFile(otherFile));
        assertFalse(CueLanguageServerFiles.isSupportedByCue(otherFile));
    }
}