package dev.monogon.cue.lang.editor.formatter;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import dev.monogon.cue.CueLightTest;
import dev.monogon.cue.cli.DefaultCueCommandService;
import org.junit.Assume;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class DefaultCueFmtServiceTest extends CueLightTest {
    @Test
    public void cueFmt() throws ExecutionException {
        Assume.assumeTrue(PathEnvironmentVariableUtil.findInPath("cue") != null);

        var newContent = new DefaultCueCommandService().format("{        a          :            b}", 5, TimeUnit.SECONDS);
        assertEquals("{a: b}\n", newContent);
    }
}