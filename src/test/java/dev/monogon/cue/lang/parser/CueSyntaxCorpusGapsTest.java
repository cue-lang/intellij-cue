package dev.monogon.cue.lang.parser;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

/**
 * Checks that KNOWN_GAPS of the CUE syntax corpus names only corpus files that
 * must be valid; see {@link CueSyntaxCorpusTest}.
 */
public class CueSyntaxCorpusGapsTest {
    @Test
    public void knownGapsNameCorpusFiles() throws IOException {
        for (var name : CueSyntaxCorpusTest.knownGaps()) {
            assertTrue("KNOWN_GAPS names " + name + ", which is not a corpus file",
                       Files.isRegularFile(CueSyntaxCorpusTest.rootPath().resolve(name)));
            assertTrue("KNOWN_GAPS names " + name + ", which no language version accepts",
                       CueSyntaxCorpusTest.mustBeValid(name));
        }
    }
}
