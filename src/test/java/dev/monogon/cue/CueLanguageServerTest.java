package dev.monogon.cue;

import com.intellij.platform.lsp.api.LspServer;
import com.intellij.platform.lsp.api.LspServerManager;
import com.intellij.util.WaitFor;
import dev.monogon.cue.cli.CueCommandService;
import dev.monogon.cue.lsp.CueServerSupportProvider;
import dev.monogon.cue.settings.CueLocalSettingsService;
import dev.monogon.cue.settings.CueLspSupport;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;

/**
 * This is an abstract base class for tests relying on CUE LSP.
 * If `cue` is unavailable on the current system, then tests will be ignored.
 *
 */
@SuppressWarnings("UnstableApiUsage")
public abstract class CueLanguageServerTest extends CueLightTest {
    @Override
    protected final boolean runInDispatchThread() {
        return false;
    }

    @Before
    public void lspSetupRequirements() {
        Assume.assumeTrue("LSP is not supported by the IDE setup", CueLspSupport.isLspSupportAvailable());
        Assume.assumeTrue("CUE LSP is turned off in the settings", CueLocalSettingsService.getSettings().isLspEnabled());
        Assume.assumeTrue("The cue binary is unavailable", CueCommandService.getInstance().isCueAvailable());

        assertFalse("No CUE LSP server must be running before the test", isCueLanguageServerRunning());
    }

    @After
    public void terminateCueLanguageServer() {
        LspServerManager.getInstance(getProject()).stopServers(CueServerSupportProvider.class);
    }

    /**
     * Waits until a CUE LSP server was launched.
     */
    protected void assertCueLanguageServer() {
        assertCueLanguageServer(5_000);
    }

    /**
     * Waits until a CUE LSP server was launched.
     */
    protected void assertCueLanguageServer(int timeoutMillis) {
        new WaitFor(timeoutMillis) {
            @Override
            protected boolean condition() {
                return isCueLanguageServerRunning();
            }
        }.assertCompleted("A CUE LSP server must be running");
    }

    /**
     * Asserts that no CUE language server is launched during the timeout.
     */
    protected void assertNoCueLanguageServer(int timeoutMillis) {
        new WaitFor(timeoutMillis) {
            @Override
            protected boolean condition() {
                return !isCueLanguageServerRunning();
            }
        }.assertCompleted("No CUE LSP server must be launched");
    }

    protected boolean isCueLanguageServerRunning() {
        return findCueLanguageServer() != null;
    }

    private @Nullable LspServer findCueLanguageServer() {
        var servers = LspServerManager.getInstance(getProject()).getServersForProvider(CueServerSupportProvider.class);
        return !servers.isEmpty() ? servers.iterator().next() : null;
    }
}
