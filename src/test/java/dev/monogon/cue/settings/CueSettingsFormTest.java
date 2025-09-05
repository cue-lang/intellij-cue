package dev.monogon.cue.settings;

import dev.monogon.cue.CueLightTest;
import org.junit.Test;

public class CueSettingsFormTest extends CueLightTest {
    @Test
    public void applySettings() {
        var settings = new CueLocalSettings();
        settings.setCueExecutablePath("/path/to/cue");
        settings.setLspEnabled(false);

        var form = new CueSettingsForm();
        form.applyFrom(settings);

        var newSettings = new CueLocalSettings();
        form.applyTo(newSettings);
        assertEquals(settings, newSettings);
    }

    @Test
    public void nullableCuePath() {
        var settings = new CueLocalSettings();

        // a null path must be set back to the settings as a null path, not an empty path
        var form = new CueSettingsForm();
        form.applyFrom(settings);

        var newSettings = new CueLocalSettings();
        form.applyTo(newSettings);
        assertNull(newSettings.getCueExecutablePath());

        assertEquals("Settings with an empty path must match, empty string is invalid", settings, newSettings);
    }
}