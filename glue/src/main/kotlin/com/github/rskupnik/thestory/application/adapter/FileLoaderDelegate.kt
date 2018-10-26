package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.file.FileHandle
import com.github.rskupnik.thestory.external.file.FileLoader

internal class FileLoaderDelegate : ImplementationDelegate<FileLoader>(), FileLoader {

    override fun getFileHandle(path: String): FileHandle? = getImplementation().getFileHandle(path)

    override fun getHandleToModule(module: String): FileHandle? = getImplementation().getHandleToModule(module)

    override fun loadAsString(fileHandle: FileHandle): String = getImplementation().loadAsString(fileHandle)

    override fun loadAsImage(fileHandle: FileHandle): Image = getImplementation().loadAsImage(fileHandle)

    override fun loadSnapshot(filename: String): Map<String, Any>? = getImplementation().loadSnapshot(filename)
}