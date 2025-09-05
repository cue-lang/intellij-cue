package dev.monogon.cue.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import dev.monogon.cue.Messages;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public final class CueApplicationConfigurable implements Configurable {
    private final CueSettingsForm form = new CueSettingsForm();
    private final boolean initialLspEnabledState;

    public CueApplicationConfigurable() {
        var settings = CueLocalSettingsService.getSettings();
        initialLspEnabledState = settings.isLspEnabled();

        form.applyFrom(settings);
    }

    @Override
    public String getDisplayName() {
        return Messages.get("applicationSettings.displayName");
    }

    @Override
    public @Nullable JComponent createComponent() {
        return form.getMainPanel();
    }

    @Override
    public boolean isModified() {
        CueLocalSettings settings = new CueLocalSettings();
        form.applyTo(settings);
        return !CueLocalSettingsService.getSettings().equals(settings);
    }

    @Override
    public void apply() {
        form.applyTo(CueLocalSettingsService.getSettings());
    }

    @Override
    public void disposeUIResources() {
        var newEnableLspValue = CueLocalSettingsService.getSettings().isLspEnabled();
        if (newEnableLspValue != initialLspEnabledState) {
            ApplicationManager.getApplication().getMessageBus().syncPublisher(CueSettingsListener.TOPIC).lspStateChanged(newEnableLspValue);
        }
    }
}
