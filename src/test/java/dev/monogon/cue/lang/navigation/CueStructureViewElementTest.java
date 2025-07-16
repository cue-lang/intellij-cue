package dev.monogon.cue.lang.navigation;

import dev.monogon.cue.CueLightTest;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.intellij.testFramework.PlatformTestUtil.assertTreeEqual;
import static com.intellij.testFramework.PlatformTestUtil.expandAll;

public class CueStructureViewElementTest extends CueLightTest {
    @Test
    public void testStructuredView() {
        doTest();
    }

    private void doTest() {
        var expectedFile = Paths.get(myFixture.getTestDataPath() + "/lang/cue/navitation/" + getTestName(true) + ".txt");
        myFixture.configureByFile("/lang/cue/navitation/" + getTestName(true) + ".cue");
        myFixture.testStructureView(svc -> {
            expandAll(svc.getTree());
            try {
                String expected = Files.readString(expectedFile);
                assertTreeEqual(svc.getTree(), expected);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}