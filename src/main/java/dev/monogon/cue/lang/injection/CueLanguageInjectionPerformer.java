package dev.monogon.cue.lang.injection;

import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionPerformer;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import dev.monogon.cue.lang.psi.CueStringLiteral;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CueLanguageInjectionPerformer implements LanguageInjectionPerformer {
    @Override
    public boolean isPrimary() {
        return true;
    }

    @Override
    public boolean performInjection(@NotNull MultiHostRegistrar registrar, @NotNull Injection injection, @NotNull PsiElement context) {
        if (!(context instanceof CueStringLiteral literal) || !literal.isValidHost()) {
            return false;
        }

        var targetLanguage = injection.getInjectedLanguage();
        if (targetLanguage == null) {
            return false;
        }

        var ranges = findInjectionRanges(literal);
        if (!ranges.isEmpty()) {
            registrar.startInjecting(targetLanguage);
            ranges.forEach(range -> registrar.addPlace(range.prefix(), range.suffix(), (PsiLanguageInjectionHost)context, range.range()));
            registrar.doneInjecting();
        }

        return true;
    }

    static List<InjectionData> findInjectionRanges(@NotNull CueStringLiteral context) {
        var totalRange = context.getLiteralContentRange();
        var interpolations = context.getInterpolationList()
            .stream()
            .map(PsiElement::getTextRangeInParent)
            .sorted(Comparator.comparingInt(TextRange::getStartOffset))
            .toList();

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
