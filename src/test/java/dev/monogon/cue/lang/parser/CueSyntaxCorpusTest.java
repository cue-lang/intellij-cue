package dev.monogon.cue.lang.parser;

import com.intellij.lang.LanguageBraceMatching;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.ParsingTestCase;
import dev.monogon.cue.CueTests;
import dev.monogon.cue.lang.CueLanguage;
import dev.monogon.cue.lang.CueParserDefinition;
import dev.monogon.cue.lang.editor.CueBraceMatcher;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parses each file of the copy of the CUE syntax corpus in
 * src/test/data/lang/cue/syntax and compares its PSI with the golden next to it.
 * Each corpus file starts with a header stating CUE's parser outcome for each
 * run of language versions, one line per run, as in
 *
 * <pre>
 * // versions: v0.8.0..v0.17.0; outcome: ok
 * // versions: v0.18.0..; outcome: error 3:2: old-style alias syntax ...
 * </pre>
 *
 * A file must be valid if a header line has the outcome ok: some language
 * version accepts it, perhaps only with the experiments the line names.
 * A file that no language version accepts has to have a parse error.
 * A file that must be valid has to parse without errors,
 * unless KNOWN_GAPS lists it: KNOWN_GAPS names, one per line, the corpus files
 * that must be valid but that the parser does not yet handle, and its absence
 * means there are none. A listed file that parses without errors fails, so the
 * list cannot go stale; when the tests run with the system property
 * idea.tests.overwrite.data set to true, which also rewrites the goldens, such a
 * file is removed from KNOWN_GAPS instead, and the file itself once it lists
 * none. syncSyntaxCorpus in build.gradle.kts refreshes the copy.
 */
@RunWith(Parameterized.class)
public class CueSyntaxCorpusTest extends ParsingTestCase {
    private static final String OVERWRITE_PROPERTY = "idea.tests.overwrite.data";
    private static final String HEADER_PREFIX = "// versions: ";
    private static final Pattern OK_LINE = Pattern.compile("// versions: [^;]*(; experiments: [^;]*)?; outcome: ok");

    private final String filePath;

    public CueSyntaxCorpusTest(@NotNull String filePath) {
        super("", "cue", true, new CueParserDefinition());
        this.filePath = filePath;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Error recovery groups tokens by matching braces when the language has a brace matcher.
        // Register it here, as the IDE does, so that the result does not depend on the tests run before.
        addExplicitExtension(LanguageBraceMatching.INSTANCE, CueLanguage.INSTANCE, new CueBraceMatcher());
    }

    @Test
    public void parser() throws IOException {
        var path = FileUtil.toSystemIndependentName(filePath);
        assertFalse(path + " has no header; refresh the copy with ./gradlew syncSyntaxCorpus", header(path).isEmpty());
        doTest(true, false);
        var hasErrors = PsiTreeUtil.hasErrorElements(myFile);
        if (!mustBeValid(path)) {
            assertTrue(path + " parses without errors, but no language version accepts it; fix the parser", hasErrors);
            return;
        }
        if (knownGaps().contains(path)) {
            if (!hasErrors && Boolean.getBoolean(OVERWRITE_PROPERTY)) {
                removeKnownGap(path);
                return;
            }
            assertTrue(path + " parses without errors; rerun the tests with -D" + OVERWRITE_PROPERTY +
                       "=true to remove it from KNOWN_GAPS", hasErrors);
        }
        else {
            assertFalse(path + " has parse errors; fix the parser, or list the file in KNOWN_GAPS", hasErrors);
        }
    }

    /**
     * Reports whether the corpus file at the given path, relative to the corpus
     * root, must parse without errors: whether a line of its header has the
     * outcome ok.
     */
    static boolean mustBeValid(@NotNull String path) throws IOException {
        return header(path).stream().anyMatch(line -> OK_LINE.matcher(line).matches());
    }

    /**
     * Returns the header lines of the corpus file at the given path, relative to
     * the corpus root.
     */
    static List<String> header(@NotNull String path) throws IOException {
        return Files.readAllLines(rootPath().resolve(path))
            .stream()
            .takeWhile(line -> line.startsWith(HEADER_PREFIX))
            .collect(Collectors.toList());
    }

    /**
     * Returns the corpus files listed in KNOWN_GAPS, as paths relative to the
     * corpus root: files that must be valid but that the parser does not yet
     * handle.
     */
    static Set<String> knownGaps() throws IOException {
        var file = rootPath().resolve("KNOWN_GAPS");
        if (!Files.exists(file)) {
            return Set.of();
        }
        return Files.readAllLines(file)
            .stream()
            .filter(CueSyntaxCorpusTest::isKnownGapEntry)
            .map(String::trim)
            .collect(Collectors.toSet());
    }

    /**
     * Removes the given path from KNOWN_GAPS, keeping its comments, and deletes
     * KNOWN_GAPS when it lists no file any more.
     */
    private static void removeKnownGap(@NotNull String path) throws IOException {
        var file = rootPath().resolve("KNOWN_GAPS");
        List<String> lines = Files.readAllLines(file)
            .stream()
            .filter(line -> !line.trim().equals(path))
            .collect(Collectors.toList());
        if (lines.stream().noneMatch(CueSyntaxCorpusTest::isKnownGapEntry)) {
            Files.delete(file);
        }
        else {
            Files.write(file, lines);
        }
    }

    private static boolean isKnownGapEntry(@NotNull String line) {
        var trimmed = line.trim();
        return !trimmed.isEmpty() && !trimmed.startsWith("#");
    }

    @Override
    protected String getTestDataPath() {
        return rootPath().toString();
    }

    @Override
    protected @NotNull String getTestName(boolean lowercaseFirstLetter) {
        return StringUtil.trimEnd(filePath, ".cue");
    }

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<String> files() {
        return CueTests.findTestFiles(rootPath());
    }

    static @NotNull Path rootPath() {
        return CueTests.findTestDataPath("lang", "cue", "syntax");
    }
}
