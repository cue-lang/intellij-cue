package dev.monogon.cue.cli;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.text.StringUtil;
import dev.monogon.cue.Messages;
import dev.monogon.cue.settings.CueLocalSettingsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DefaultCueCommandService implements CueCommandService {
    @Override
    public boolean isCueAvailable() {
        return findCueBinaryPath() != null;
    }

    @Override
    public @Nullable String format(@NotNull String content, long timeout, TimeUnit unit) throws ExecutionException {
        var cuePath = findCueBinaryPath();
        if (cuePath == null) {
            throw new ExecutionException(Messages.get("cue.binary.notFoundOrNotExecutable"));
        }

        try {
            GeneralCommandLine cmd = new GeneralCommandLine(cuePath.toString(), "fmt", "-");
            cmd.withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE);
            cmd.withCharset(StandardCharsets.UTF_8);

            // the process handler already calls processTerminated in a background thread,
            // so we don't start another background process
            CapturingProcessHandler processHandler = new CapturingProcessHandler(cmd);
            try (var stdin = processHandler.getProcessInput()) {
                stdin.write(content.getBytes(StandardCharsets.UTF_8));
                stdin.flush();
            }

            ProcessOutput output;
            var indicator = ProgressManager.getGlobalProgressIndicator();
            if (indicator != null) {
                output = processHandler.runProcessWithProgressIndicator(indicator, (int)unit.toMillis(timeout), true);
            }
            else {
                output = processHandler.runProcess((int)unit.toMillis(timeout), true);
            }

            if (output.isTimeout() || !output.isExitCodeSet() || output.getExitCode() != 0) {
                return null;
            }
            return output.getStdout();
        }
        catch (IOException e) {
            throw new ExecutionException(Messages.get("formatter.cueExecuteError"), e);
        }
    }

    @Override
    public @Nullable GeneralCommandLine createLSPCommandLine() {
        var cuePath = findCueBinaryPath();
        if (cuePath == null) {
            return null;
        }

        return new GeneralCommandLine(cuePath.toString())
            .withParameters("lsp", "serve")
            .withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE);
    }

    private @Nullable Path findCueBinaryPath() {
        Path cueBinaryPath = null;

        var configuredPath = CueLocalSettingsService.getSettings().getCueExecutablePath();
        if (StringUtil.isNotEmpty(configuredPath)) {
            cueBinaryPath = Paths.get(configuredPath);
        }
        else {
            var envPath = PathEnvironmentVariableUtil.findInPath("cue");
            if (envPath != null) {
                cueBinaryPath = envPath.toPath();
            }
        }

        return cueBinaryPath != null && Files.isExecutable(cueBinaryPath) ? cueBinaryPath : null;
    }
}
