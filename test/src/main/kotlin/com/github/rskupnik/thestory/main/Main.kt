package com.github.rskupnik.thestory.main

import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.shared.external.CallbackReceiver
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel
import com.github.rskupnik.thestory.shared.external.file.FileHandle

fun main(args: Array<String>) {
    // For ad-hoc testing
    val api = Application.init()
    api.provideImplementation(AssetLoader::class, DummyAssetLoader())
    api.provideImplementation(CallbackReceiver::class, CallbackReceiverImpl())
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