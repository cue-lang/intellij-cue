package dev.monogon.cue.lsp;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.ProjectScope;
import com.intellij.util.concurrency.ThreadingAssertions;
import com.intellij.util.concurrency.annotations.RequiresReadLock;
import dev.monogon.cue.lang.CueFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Shared handling of files supported by CUE.
 */
public final class CueLanguageServerFiles {
    private static final Set<String> SUPPORTED_COMPLETION_FILE_EXTENSIONS = Set.of("json", "yml", "yaml");
    public static final String CUE_EXTENSION = "cue";

    private CueLanguageServerFiles() {
    }

    /**
     * @return {@code true} if the {@code file} is a CUE file.
     */
    public static boolean isCueFile(@NotNull VirtualFile file) {
        return CueFileType.INSTANCE.equals(file.getFileType());
    }

    /**
     * Returns if CUE would be able to provide data for the given {@code file},
     * for example if CUE LSP is supporting code completions in the file.
     * <p>
     * This method need a ReadAction and accesses indexes to locate CUE files if a non-CUE file is checked.
     * For example, JSON files are only supported if at least one *.cue file in the project.
     * We don't want to declare support for JSON and other files if CUE is not in use in the project.
     *
     * @param file The edited file
     * @return {@code true} if CUE is supporting the file in any way.
     */
    @RequiresReadLock
    public static boolean isSupportedByCue(@NotNull Project project, @NotNull VirtualFile file) {
        ThreadingAssertions.assertReadAccess();
        return isCueFile(file) || isSupportedByCompletions(file) && hasIndexedCueFile(project);
    }

    private static boolean hasIndexedCueFile(@NotNull Project project) {
        var foundCue = new AtomicBoolean(false);
        FilenameIndex.processAllFileNames(name -> {
            if (FileUtil.extensionEquals(name, CUE_EXTENSION)) {
                foundCue.set(true);
                return false;
            }
            return true;
        }, ProjectScope.getContentScope(project), null);
        return foundCue.get();
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
