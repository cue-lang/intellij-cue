package dev.monogon.cue.lsp;

import com.intellij.openapi.vfs.VirtualFile;
import dev.monogon.cue.lang.CueFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Shared handling of files supported by CUE.
 */
public final class CueLanguageServerFiles {
    private static final Set<String> SUPPORTED_COMPLETION_FILE_EXTENSIONS = Set.of("json", "yaml");

    private CueLanguageServerFiles() {
    }

    public static boolean isCueFile(@NotNull VirtualFile file) {
        return CueFileType.INSTANCE.equals(file.getFileType());
    }

    /**
     * Returns whether a file should be passed to CUE via textDocument events.
     *
     * @param file The edited file
     * @return {@code true} if CUE is supporting the file in any way.
     */
    public static boolean isSupportedByCue(@NotNull VirtualFile file) {
        return isCueFile(file) || isSupportedByCompletions(file);
    }

    /**
     * Returns if CUE supports code completion for the given file.
     * We're only checking the extension because the IDE's file type won't align with CUE's implementation.
     */
    private static boolean isSupportedByCompletions(@NotNull VirtualFile file) {
        var extension = file.getExtension();
        return extension != null && SUPPORTED_COMPLETION_FILE_EXTENSIONS.contains(extension);
    }
}
