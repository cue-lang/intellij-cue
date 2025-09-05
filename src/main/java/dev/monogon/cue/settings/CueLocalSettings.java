package dev.monogon.cue.settings;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Settings local to the current machine and environment.
 */
public class CueLocalSettings {
    @Nullable
    private volatile String cueExecutablePath;
    private boolean lspEnabled = true;

    @Nullable
    public String getCueExecutablePath() {
        return StringUtil.nullize(cueExecutablePath);
    }

    public void setCueExecutablePath(@Nullable String path) {
        this.cueExecutablePath = StringUtil.nullize(path);
    }

    public boolean isLspEnabled() {
        return lspEnabled;
    }

    public void setLspEnabled(boolean lspEnabled) {
        this.lspEnabled = lspEnabled;
    }

    public void applyFrom(@NotNull CueLocalSettings state) {
        setCueExecutablePath(state.cueExecutablePath);
        setLspEnabled(state.lspEnabled);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CueLocalSettings that = (CueLocalSettings)o;
        return lspEnabled == that.lspEnabled && Objects.equals(cueExecutablePath, that.cueExecutablePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cueExecutablePath, lspEnabled);
    }
}
