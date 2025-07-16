package dev.monogon.cue.lang.editor.folding;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.APP)
@State(name = "cue-folding", storages = @Storage("cue-folding.xml"))
public final class CueFoldingSettingsService implements PersistentStateComponent<CueFoldingSettings> {
    @NotNull
    private final CueFoldingSettings state = new CueFoldingSettings();

    @Override
    public @NotNull CueFoldingSettings getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull CueFoldingSettings state) {
        this.state.applyFrom(state);
    }

    public static @NotNull CueFoldingSettings getSettings() {
        return ApplicationManager.getApplication().getService(CueFoldingSettingsService.class).state;
    }
}
