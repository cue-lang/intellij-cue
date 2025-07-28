package dev.monogon.cue.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import org.jetbrains.annotations.Nullable;

public class CueServerCommandLine {
    public static boolean isServerAvailable() {
        return PathEnvironmentVariableUtil.findInPath("cue") != null;
    }

    public static @Nullable GeneralCommandLine createCueServerCommand() {
        var cuePath = PathEnvironmentVariableUtil.findInPath("cue");
        if (cuePath == null) {
            return null;
        }

        return new GeneralCommandLine(cuePath.getAbsolutePath()).withParameters("lsp");
    }
}
