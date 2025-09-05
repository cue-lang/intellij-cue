package dev.monogon.cue.lsp;

import dev.monogon.cue.settings.CueLspSupport;

public final class CueJetBrainsLspSupport implements CueLspSupport {
    @Override
    public boolean isLspSupported() {
        return true;
    }
}
