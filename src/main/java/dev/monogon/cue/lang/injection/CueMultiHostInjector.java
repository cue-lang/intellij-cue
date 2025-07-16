package dev.monogon.cue.lang.injection;

import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import dev.monogon.cue.lang.psi.CueStringLiteral;
import org.intellij.plugins.intelliLang.inject.InjectorUtils;
import org.intellij.plugins.intelliLang.inject.LanguageInjectionSupport;
import org.intellij.plugins.intelliLang.inject.TemporaryPlacesRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Injector with knowledge of interpolations in CUE string literals.
 * Language injection skips the interpolations.
 * <p>
 * The default feature to use comments to define language, prefix, and suffix is supported
 * by leveraging the IntelliLang plugin.
 */
public class CueMultiHostInjector implements MultiHostInjector {
    private static final List<Class<CueStringLiteral>> classes = Collections.singletonList(CueStringLiteral.class);

    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (!(context instanceof CueStringLiteral literal)) {
            return;
        }

        if (!literal.isValidHost()) {
            return;
        }

        Optional<LanguageInjectionSupport> injectionSupport = InjectorUtils.getActiveInjectionSupports()
            .stream()
            .filter(support -> support.isApplicableTo(literal) && support.useDefaultCommentInjector())
            .findFirst();

        Language targetLanguage = null;
        if (injectionSupport.isPresent()) {
            var injection = injectionSupport.get().findCommentInjection(context, null);
            if (injection != null) {
                targetLanguage = injection.getInjectedLanguage();
            }
        }

        // our fallbacks: temporary places first
        if (targetLanguage == null) {
            var hostFile = context.getContainingFile();
            var project = hostFile.getProject();
            var tempInjection = TemporaryPlacesRegistry.getInstance(project).getLanguageFor(literal, hostFile);
            if (tempInjection != null) {
                targetLanguage = tempInjection.getLanguage();
            }
        }

        // fallback: generic comment implementation
        if (targetLanguage == null) {
            var baseInjection = InjectorUtils.findCommentInjection(context, "comment", null);
            if (baseInjection != null) {
                targetLanguage = baseInjection.getInjectedLanguage();
            }
        }

        // finally, do the injection if a language was found
        if (targetLanguage != null) {
            var ranges = findInjectionRanges(literal);
            if (!ranges.isEmpty()) {
                registrar.startInjecting(targetLanguage);
                var last = ranges.size() - 1;
                for (int i = 0; i <= last; i++) {
                    var range = ranges.get(i);
                    registrar.addPlace(range.prefix, range.suffix, (PsiLanguageInjectionHost)context, range.range);
                }
                registrar.doneInjecting();
            }
        }
    }

    @Override
    public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return classes;
    }

    static List<InjectionData> findInjectionRanges(CueStringLiteral context) {
        var totalRange = context.getLiteralContentRange();
        var interpolations = context.getInterpolationList()
            .stream()
            .map(PsiElement::getTextRangeInParent)
            .sorted(Comparator.comparingInt(TextRange::getStartOffset))
            .collect(Collectors.toList());

        if (interpolations.isEmpty()) {
            return Collections.singletonList(new InjectionData(totalRange, null, null));
        }

        var hostText = context.getText();
        var result = new LinkedList<InjectionData>();
        var lastStart = totalRange.getStartOffset();
        var lastEnd = totalRange.getStartOffset();
        for (TextRange range : interpolations) {
            var start = range.getStartOffset();
            var end = range.getEndOffset();
            if (start == totalRange.getStartOffset()) {
                // interpolation at start, insert empty range before to allow editing
                result.add(new InjectionData(TextRange.create(start, start), null, null));
            }
            else if (start > lastEnd) {
                var prefix = lastEnd > lastStart ? hostText.substring(lastStart, lastEnd) : null;
                result.add(new InjectionData(TextRange.create(lastEnd, start), prefix, null));
            }
            lastStart = start;
            lastEnd = end;
        }
        if (lastEnd < totalRange.getEndOffset()) {
            var prefix = lastEnd > lastStart ? hostText.substring(lastStart, lastEnd) : null;
            result.add(new InjectionData(TextRange.create(lastEnd, totalRange.getEndOffset()), prefix, null));
        }
        else if (lastEnd == totalRange.getEndOffset() && lastEnd > lastStart) {
            var prefix = hostText.substring(lastStart, lastEnd);
            result.add(new InjectionData(TextRange.create(lastEnd, totalRange.getEndOffset()), prefix, null));
        }
        return result;
    }
}
