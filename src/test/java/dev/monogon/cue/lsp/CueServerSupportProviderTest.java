package dev.monogon.cue.lsp;

import com.intellij.testFramework.IndexingTestUtil;
import dev.monogon.cue.CueLanguageServerTest;
import org.junit.Test;

public class CueServerSupportProviderTest extends CueLanguageServerTest {
    @Test
    public void cueFileLaunchesServer() {
        createCueFile("");
        assertCueLanguageServer();

        assertTrue(isCueLanguageServerRunning());
    }

    @Test
    public void jsonWithoutCueMustNotLaunchServer() {
        myFixture.configureByText("test.json", "");
        assertNoCueLanguageServer(2_000);
    }

    @Test
    public void jsonWithCueMustLaunchServer() {
        // createFile to avoid opening it in an editor, which would trigger the launch of CUE LSP
        myFixture.createFile("test.cue", "");
        IndexingTestUtil.waitUntilIndexesAreReady(getProject());

        assertNoCueLanguageServer(1_000);

        // opening a JSON file with at least one .cue file in the project must launch CUE LSP
        myFixture.configureByText("test.json", "");
        assertCueLanguageServer(2_000);
    }
}