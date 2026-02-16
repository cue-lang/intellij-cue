package dev.monogon.cue.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor;
import dev.monogon.cue.Messages;
import dev.monogon.cue.cli.CueCommandService;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

class CueServerDescriptor extends ProjectWideLspServerDescriptor {
    public CueServerDescriptor(@NotNull Project project) {
        super(project, Messages.get("cue.lsp.descriptor.name"));
    }

    @Override
    public boolean isSupportedFile(@NotNull VirtualFile virtualFile) {
        return CueLanguageServerFiles.isSupportedByCue(virtualFile);
    }

    @Override
    public @NotNull GeneralCommandLine createCommandLine() {
        // the availability of the cue binary was already checked in isSupportedFile()
        return Objects.requireNonNull(CueCommandService.getInstance().createLSPCommandLine());
    }
}
