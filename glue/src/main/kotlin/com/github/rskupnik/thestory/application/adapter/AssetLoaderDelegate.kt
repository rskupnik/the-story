package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.external.file.FileHandle

internal class AssetLoaderDelegate : ImplementationDelegate<AssetLoader>(), AssetLoader {

    override fun loadImageProvider(fileHandle: FileHandle): ImageProvider = getImplementation()?.loadImageProvider(fileHandle) ?: throw IllegalStateException("Implementation not provided")
}