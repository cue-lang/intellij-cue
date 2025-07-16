package dev.monogon.cue.cli;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.progress.ProgressManager;
import dev.monogon.cue.Messages;
import dev.monogon.cue.settings.CueLocalSettingsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DefaultCueCommandService implements CueCommandService {
    @Override
    public @Nullable String format(@NotNull String content, long timeout, TimeUnit unit) throws ExecutionException {
        String cuePath = CueLocalSettingsService.getSettings().getCueExecutablePath();
        if (cuePath == null || cuePath.isEmpty()) {
            var envPath = PathEnvironmentVariableUtil.findInPath("cue");
            if (envPath == null || !envPath.canExecute()) {
                throw new ExecutionException(Messages.get("formatter.exeNotFound"));
            }
            cuePath = envPath.getAbsolutePath();
        }
        else {
            if (!Files.isExecutable(Paths.get(cuePath))) {
                throw new ExecutionException(Messages.get("formatter.userPathNotFound"));
            }
        }

        try {
            GeneralCommandLine cmd = new GeneralCommandLine(cuePath, "fmt", "-");
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
}
