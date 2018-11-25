package com.github.rskupnik.thestory.external.asset

import com.github.rskupnik.thestory.external.Port
import com.github.rskupnik.thestory.external.file.FileHandle

/**
 * Handles loading [Asset]s from disk.
 */
interface AssetLoader : Port {

    /**
     * Load an [ImageProvider] pointed at by [fileHandle].
     */
    fun loadImageProvider(fileHandle: FileHandle): ImageProvider
}