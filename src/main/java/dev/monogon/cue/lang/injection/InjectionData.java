package dev.monogon.cue.lang.injection;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

record InjectionData(@NotNull TextRange range, @Nullable String prefix, @Nullable String suffix) {
}
