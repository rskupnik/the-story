package com.github.rskupnik.thestory.implementations.inmemory

import com.github.rskupnik.thestory.external.file.FileSaver
import org.jetbrains.annotations.NotNull

class InMemoryFileSaver implements FileSaver {

    private final Map<String, Object> objects = new HashMap<>()

    @Override
    void saveSnapshot(@NotNull String filename, @NotNull Map<String, ?> state) {
        objects.put(filename, state)
    }

    Object get(String filename) {
        return objects.get(filename)
    }
}
