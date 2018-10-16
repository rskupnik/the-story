package com.github.rskupnik.thestory.main

import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.shared.external.CallbackReceiver
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.asset.Image
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel
import com.github.rskupnik.thestory.shared.external.file.FileHandle
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import java.io.InputStream

fun main(args: Array<String>) {
    // For ad-hoc testing
    val api = Application.init()
    api.provideImplementation(AssetLoader::class, DummyAssetLoader())
    api.provideImplementation(CallbackReceiver::class, CallbackReceiverImpl())
    api.provideImplementation(FileLoader::class, DummyFileLoader())
    api.getCommandAPI().initializeGame("demo")
    //val availableModules = api.getQueryAPI().getAvailableModules()
    //availableModules.availableModules.forEach(::println)
}

class CallbackReceiverImpl : CallbackReceiver {
    override fun onDisplayOptions(options: List<OptionLabel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DummyAssetLoader : AssetLoader {
    override fun loadImageProvider(fileHandle: FileHandle): ImageProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DummyFileLoader : FileLoader {
    override fun getFileHandle(path: String): FileHandle? = DummyFileHandle(ClassLoader.getSystemClassLoader().getResourceAsStream(path))

    override fun getHandleToModule(module: String): FileHandle? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAsString(fileHandle: FileHandle): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAsImage(fileHandle: FileHandle): Image {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadSnapshot(filename: String): Map<String, Any>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DummyFileHandle(val inputStream: InputStream) : FileHandle {

}