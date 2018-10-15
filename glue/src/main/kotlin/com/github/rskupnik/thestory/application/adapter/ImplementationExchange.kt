package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.CallbackReceiver
import com.github.rskupnik.thestory.shared.external.Port
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import kotlin.reflect.KClass

internal class ImplementationExchange : ImplementationProvider, ImplementationSupplier {

    private val delegates: Map<KClass<out Port>, ImplementationDelegate<out Port>> = mapOf(
        FileLoader::class to FileLoaderDelegate(),
        AssetLoader::class to AssetLoaderDelegate(),
        CallbackReceiver::class to CallbackReceiverDelegate()
    )

    override fun <T : Port> provideImplementation(klass: KClass<T>, implementation: T) {
        (delegates[klass] as ImplementationDelegate<T>).setImplementation(implementation)
    }

    override fun <T : Port> getImplementation(klass: KClass<T>): T {
        val impl = delegates[klass] ?: throw IllegalStateException("Implementation not provided for $klass")
        return impl as T
    }
}