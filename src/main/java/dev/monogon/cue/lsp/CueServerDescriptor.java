package dev.monogon.cue.lsp;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor;
import dev.monogon.cue.lang.CueFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("UnstableApiUsage")
public class CueServerDescriptor extends ProjectWideLspServerDescriptor {
    public CueServerDescriptor(@NotNull Project project) {
        super(project, "CUE");
    }

    @Override
    public boolean isSupportedFile(@NotNull VirtualFile virtualFile) {
        return CueFileType.INSTANCE.equals(virtualFile.getFileType()) && CueServerCommandLine.isServerAvailable();
    }

    @Override
    public @NotNull GeneralCommandLine createCommandLine() throws ExecutionException {
        return Objects.requireNonNull(CueServerCommandLine.createCueServerCommand());
    }
}
