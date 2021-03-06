package com.github.rskupnik.thestory.implementations.classpath

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.file.FileHandle
import com.github.rskupnik.thestory.external.file.FileLoader
import org.jetbrains.annotations.NotNull

class ClasspathFileLoader implements FileLoader {

    @Override
    FileHandle getFileHandle(@NotNull String path) {
        final URL url = this.getClass().getResource("/" + path)
        if (url != null)
            return new ClasspathFileHandle(url)
        else return null
    }

    @Override
    FileHandle getHandleToModule(@NotNull String module) {
        return null
    }

    @Override
    String loadAsString(@NotNull FileHandle fileHandle) {
        return ((ClasspathFileHandle) fileHandle).url.text
    }

    @Override
    Image loadAsImage(@NotNull FileHandle fileHandle) {
        return null
    }

    @Override
    Map<String, Object> loadSnapshot(@NotNull String filename) {
        def handle = getFileHandle(filename)
        if (handle == null) return null

        def content = loadAsString(handle)

        def objMapper = new ObjectMapper()
        def snapshot = objMapper.readValue(content, Map.class)

        return snapshot
    }
}
