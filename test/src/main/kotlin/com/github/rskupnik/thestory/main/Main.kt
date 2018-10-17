package com.github.rskupnik.thestory.main

import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.shared.external.CallbackReceiver
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.asset.Image
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel
import com.github.rskupnik.thestory.shared.external.file.FileHandle
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import java.io.File
import java.io.InputStream
import java.nio.file.Files

fun main(args: Array<String>) {
    // For ad-hoc testing
    val api = Application.init()
    api.provideImplementation(AssetLoader::class, Main.DummyAssetLoader())
    api.provideImplementation(CallbackReceiver::class, Main.CallbackReceiverImpl())
    api.provideImplementation(FileLoader::class, Main.DummyFileLoader())
    api.getCommandAPI().initializeGame("demo")
    //val availableModules = api.getQueryAPI().getAvailableModules()
    //availableModules.availableModules.forEach(::println)
}

class Main {

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
        override fun getFileHandle(path: String): FileHandle? = DummyFileHandle(Main::class.java.classLoader.getResourceAsStream(path))

        override fun getHandleToModule(module: String): FileHandle? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loadAsString(fileHandle: FileHandle): String = (fileHandle as DummyFileHandle).inputStream.readBytes().toString(Charsets.UTF_8)

        override fun loadAsImage(fileHandle: FileHandle): Image {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loadSnapshot(filename: String): Map<String, Any>? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class DummyFileHandle(val inputStream: InputStream) : FileHandle {

    }
}