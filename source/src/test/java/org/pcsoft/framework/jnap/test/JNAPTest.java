package org.pcsoft.framework.jnap.test;

import org.junit.Ignore;
import org.junit.Test;
import org.pcsoft.framework.jnap.JNAP;
import org.pcsoft.framework.jnap.test.access.Win32;

@Ignore
public class JNAPTest {

    @Test
    public void test() {
        final var proxy = JNAP.createProxy(Win32.class);
        proxy.showMessageBox("Demo Window", "Message");
    }

}