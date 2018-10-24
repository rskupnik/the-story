package com.github.rskupnik.thestory.external.asset

import com.github.rskupnik.thestory.external.Port
import com.github.rskupnik.thestory.external.file.FileHandle

interface AssetLoader : Port {
    fun loadImageProvider(fileHandle: FileHandle): ImageProvider
}