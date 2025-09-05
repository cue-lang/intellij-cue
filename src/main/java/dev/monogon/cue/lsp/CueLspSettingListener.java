package dev.monogon.cue.lsp;

import com.intellij.openapi.project.Project;
import com.intellij.platform.lsp.api.LspServerManager;
import dev.monogon.cue.settings.CueSettingsListener;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class CueLspSettingListener implements CueSettingsListener {
    private final @NotNull Project project;

    public CueLspSettingListener(@NotNull Project project) {
        this.project = project;
    }

    @Override
    public void lspStateChanged(boolean isNowEnabled) {
        if (isNowEnabled) {
            LspServerManager.getInstance(project).startServersIfNeeded(CueServerSupportProvider.class);
        }
        else {
            LspServerManager.getInstance(project).stopServers(CueServerSupportProvider.class);
        }
    }
}
