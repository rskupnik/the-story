package com.github.rskupnik.thestory.shared.external.asset

import com.github.rskupnik.thestory.shared.external.file.FileHandle

interface AssetLoader {
    fun loadImageProvider(fileHandle: FileHandle): ImageProvider
}