package dev.monogon.cue.settings;

import com.intellij.util.messages.Topic;

public interface CueSettingsListener {
    Topic<CueSettingsListener> TOPIC = Topic.create("cue.settings", CueSettingsListener.class);

    /**
     * This is invoked after the state of the CUE LSP configuration changed.
     * It's called after changes to the {@link CueLocalSettings#isLspEnabled()} flag
     * or to the path of the CUE executable {@link CueLocalSettings#getCueExecutablePath()}.
     */
    void lspStateChanged(boolean isNowEnabled);
}
