package dev.monogon.cue.cli;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Service to interact with the cue command line tool.
 */
public interface CueCommandService {
    /**
     * Locates the cue command line tool.
     * If the path was configured by the user, this path will be returned. As a fallback, the cue binary will be searched in the PATH.
     * If found cue binary is not executable, then {@code false} is returned.
     *
     * @return Return {@code true} if the cue command line tool is available, either with a configured path or in the PATH.
     */
    boolean isCueAvailable();

    /**
     * Calls "cue fmt", writes the given content on STDIN and returns STDOUT on success (exit code 0) or an error in other cased.
     *
     * @return The formatted content, if available.
     */
    @Nullable
    String format(@NotNull String content, long timeout, TimeUnit unit) throws ExecutionException;

    /**
     * Creates a command line to launch the cue language server.
     *
     * @return The command line, if available. {@code null} if the cue binary is not available.
     */
    @Nullable
    GeneralCommandLine createLSPCommandLine();

    static @NotNull CueCommandService getInstance() {
        return ApplicationManager.getApplication().getService(CueCommandService.class);
    }
}
