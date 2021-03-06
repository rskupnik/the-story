package com.github.rskupnik.thestory.main

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.asset.ImageProvider
import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileHandle
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.script.domain.Script
import java.io.InputStream

fun main(args: Array<String>) {
    // For ad-hoc testing
    val api = Application.init()
    api.provideImplementation(AssetLoader::class.java, Main.DummyAssetLoader())
    api.provideImplementation(CallbackReceiver::class.java, Main.CallbackReceiverImpl())
    api.provideImplementation(FileLoader::class.java, Main.DummyFileLoader())
    api.provideImplementation(FileSaver::class.java, Main.DummyFileSaver())
    api.getCommandAPI().loadGame("demo.sav")
    //api.getCommandAPI().initializeGame("demo")
    //val availableModules = api.getQueryAPI().getAvailableModules()
    //availableModules.availableModules.forEach(::println)
}

class Main {

    class CallbackReceiverImpl : CallbackReceiver {
        override fun onBackgroundChanged(background: Background) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onEquipmentRefreshed() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onInventoryRefreshed() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onItemImageChanged(item: ItemView) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onItemRemoved(item: ItemView) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onNewItemFound(item: ItemView) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onItemEquipped(item: ItemView) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLocationLoaded(script: Script) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onDisplayOptions(options: List<OptionLabel>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class DummyAssetLoader : AssetLoader {
        override fun loadImageProvider(fileHandle: FileHandle): ImageProvider = DummyImageProvider()
    }

    class DummyFileLoader : FileLoader {
        override fun getFileHandle(path: String): FileHandle? =
                when (path) {
                    "modules/unpacked/demo/module.json",
                    "modules/unpacked/demoExt/module.json",
                    "modules/unpacked/demoOpt/module.json",
                    "modules/unpacked/demo/assets.atlas"
                    -> DummyFileHandle(path, Main::class.java.classLoader.getResourceAsStream(path))
                    else -> null
                }

        override fun getHandleToModule(module: String): FileHandle? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loadAsString(fileHandle: FileHandle): String = (fileHandle as DummyFileHandle).inputStream.readBytes().toString(Charsets.UTF_8)

        override fun loadAsImage(fileHandle: FileHandle): Image {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loadSnapshot(filename: String): Map<String, Any>? {
            return ObjectMapper().readValue<Map<String, Any>>(
                    """
                        {
                            "items": [
                                {
                                    "id": "7537c627-5069-4e9a-bd3d-5c3808e84ac9",
                                    "blueprint": "torch"
                                }
                            ]
                        }
                    """.trimIndent()
            )
        }
    }

    class DummyFileSaver : FileSaver {
        override fun saveSnapshot(filename: String, state: Map<String, Any?>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class DummyFileHandle(val path: String, val inputStream: InputStream) : FileHandle

    class DummyImageProvider : ImageProvider {
        override fun getImage(id: String): Image? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}