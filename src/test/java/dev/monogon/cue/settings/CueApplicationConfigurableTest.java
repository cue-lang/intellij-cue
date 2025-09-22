package dev.monogon.cue.settings;

import com.intellij.openapi.application.ApplicationManager;
import dev.monogon.cue.CueLightTest;
import org.junit.Test;

public class CueApplicationConfigurableTest extends CueLightTest {
    @Test
    public void applySettings() {
        var initialCueSettings = CueLocalSettingsService.getSettings();
        initialCueSettings.setCueExecutablePath("/path/to/cue");
        CueLocalSettingsService.getSettings().setLspEnabled(false);

        // set up the form with the global settings
        var form = new CueApplicationConfigurable();
        form.reset();

        // Apply the form to the global settings and verify that the global settings are updated correctly
        resetGlobalCueSettings();
        assertEquals(new CueLocalSettings(), CueLocalSettingsService.getSettings());

        form.apply();
        assertEquals(initialCueSettings, CueLocalSettingsService.getSettings());
    }

    @Test
    public void nullableCuePath() {
        resetGlobalCueSettings();
        var initialCueSettings = CueLocalSettingsService.getSettings();

        // a null path must be set back to the settings as a null path, not an empty path
        var form = new CueApplicationConfigurable();
        form.reset();
        form.apply();
        assertNull(CueLocalSettingsService.getSettings().getCueExecutablePath());

        assertEquals("Settings with an empty path must match, empty string is invalid",
                     initialCueSettings,
                     CueLocalSettingsService.getSettings());
    }

    private static void resetGlobalCueSettings() {
        ApplicationManager.getApplication().getService(CueLocalSettingsService.class).loadState(new CueLocalSettings());
    }
}