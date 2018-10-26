package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.asset.ImageProvider
import com.github.rskupnik.thestory.external.file.FileHandle

internal class AssetLoaderDelegate : ImplementationDelegate<AssetLoader>(), AssetLoader {

    override fun loadImageProvider(fileHandle: FileHandle): ImageProvider = getImplementation().loadImageProvider(fileHandle)
}