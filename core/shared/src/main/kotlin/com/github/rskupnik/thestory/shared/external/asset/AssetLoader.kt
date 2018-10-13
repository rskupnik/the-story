package com.github.rskupnik.thestory.shared.external.asset

import com.github.rskupnik.thestory.shared.external.Port
import com.github.rskupnik.thestory.shared.external.file.FileHandle

interface AssetLoader : Port {
    fun loadImageProvider(fileHandle: FileHandle): ImageProvider
}