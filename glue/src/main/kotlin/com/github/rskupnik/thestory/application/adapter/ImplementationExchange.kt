package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.Port
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
import kotlin.reflect.KClass

internal class ImplementationExchange : ImplementationProvider, ImplementationSupplier {

    private val delegates: Map<Class<out Port>, ImplementationDelegate<out Port>> = mapOf(
        FileLoader::class.java to FileLoaderDelegate(),
        AssetLoader::class.java  to AssetLoaderDelegate(),
        CallbackReceiver::class.java  to CallbackReceiverDelegate(),
        FileSaver::class.java  to FileSaverDelegate()
    )

    override fun <T : Port> provideImplementation(klass: Class<T>, implementation: T) {
        (delegates[klass] as ImplementationDelegate<T>).setImplementation(implementation)
    }

    override fun <T : Port> getImplementation(klass: Class<T>): T {
        val impl = delegates[klass] ?: throw IllegalStateException("Implementation not provided for $klass")
        return impl as T
    }
}