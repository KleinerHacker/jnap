package org.pcsoft.framework.jnap.test.access;

import jdk.incubator.foreign.MemoryAddress;
import org.pcsoft.framework.jnap.api.NativeCall;
import org.pcsoft.framework.jnap.api.NativeType;

public interface Win32 {

    @NativeCall(library = "user32", method = "MessageBox")
    int showMessageBox(@NativeType MemoryAddress handle, @NativeType String title, @NativeType String text, @NativeType int type);

    default int showMessageBox(@NativeType String title, @NativeType String text, @NativeType int type) {
        return showMessageBox(null, title, text, type);
    }

    default int showMessageBox(@NativeType MemoryAddress handle, @NativeType String title, @NativeType String text) {
        return showMessageBox(handle, title, text, 0);
    }

    default int showMessageBox(@NativeType String title, @NativeType String text) {
        return showMessageBox(null, title, text);
    }
}
