package dev.monogon.cue.lsp;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.LspServer;
import com.intellij.platform.lsp.api.LspServerSupportProvider;
import com.intellij.platform.lsp.api.lsWidget.LspServerWidgetItem;
import dev.monogon.cue.Icons;
import dev.monogon.cue.cli.CueCommandService;
import dev.monogon.cue.settings.CueLocalSettingsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("UnstableApiUsage")
public class CueServerSupportProvider implements LspServerSupportProvider {
    @Override
    public void fileOpened(@NotNull Project project,
                           @NotNull VirtualFile virtualFile,
                           @NotNull LspServerSupportProvider.LspServerStarter serverStarter) {
        if (!isEnabledInUnitTests.get() && ApplicationManager.getApplication().isUnitTestMode()) {
            return;
        }

        if (CueLanguageServerFiles.isSupportedByCue(project, virtualFile) &&
            CueLocalSettingsService.getSettings().isLspEnabled() &&
            CueCommandService.getInstance().isCueAvailable()) {
            serverStarter.ensureServerStarted(new CueServerDescriptor(project));
        }
    }

    @Override
    public @Nullable LspServerWidgetItem createLspServerWidgetItem(@NotNull LspServer lspServer, @Nullable VirtualFile currentFile) {
        return new LspServerWidgetItem(lspServer, currentFile, Icons.CueLogo, null);
    }

    /**
     * Allows to enabled the LSP server in unit tests.
     * By default, the server is turned off to avoid interference with other tests.
     */
    @TestOnly
    public static AtomicBoolean isEnabledInUnitTests = new AtomicBoolean(false);
}
