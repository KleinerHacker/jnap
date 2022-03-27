package org.pcsoft.framework.jnap.internal;

import jdk.incubator.foreign.CLinker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LibraryManager {
    private static final Map<String, CLinker> LIBRARY_MAP = new HashMap<>();

    public static CLinker getLibrary(String name) {
        if (!LIBRARY_MAP.containsKey(name)) {
            System.loadLibrary(name);
            LIBRARY_MAP.put(name, CLinker.systemCLinker()); //TODO: API will changed in future: use LibraryLoader
        }

        return LIBRARY_MAP.get(name);
    }
}
