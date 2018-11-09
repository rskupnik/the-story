package com.github.rskupnik.thestory.implementations.classpath

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.file.FileHandle
import com.github.rskupnik.thestory.external.file.FileLoader
import org.jetbrains.annotations.NotNull

class ClasspathFileLoader implements FileLoader {

    @Override
    FileHandle getFileHandle(@NotNull String path) {
        return new ClasspathFileHandle(this.getClass().getResource("/" + path))
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
        return null
    }
}
