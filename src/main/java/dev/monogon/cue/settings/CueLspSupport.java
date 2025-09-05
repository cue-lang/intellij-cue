package dev.monogon.cue.settings;

import com.intellij.openapi.extensions.ExtensionPointName;

/**
 * Extension to let the main plugin know if LSP support is loaded.
 */
public interface CueLspSupport {
    ExtensionPointName<CueLspSupport> EP_NAME = ExtensionPointName.create("dev.monogon.cuelang.lspSupportStatus");

    /**
     * @return {@code true} if the LSP support is available in the current IDE.
     * Our LSP support is only loaded if the IDE provides LSP support.
     */
    boolean isLspSupported();

    /**
     * @return {@code true} if the LSP support is available in the current IDE, calls {@link #isLspSupported()}.
     */
    static boolean isLspSupportAvailable() {
        return EP_NAME.findFirstSafe(CueLspSupport::isLspSupported) != null;
    }
}
