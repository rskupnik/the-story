package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.external.Port
import kotlin.reflect.KClass

internal class APIImpl(
        injector: Injector,
        internals: MutableMap<Class<out Any>, Any>? = null
) : API {

    private val queryAPI: QueryAPI = injector.root().queryAPI
    private val commandAPI: CommandAPI = injector.root().commandAPI
    private val implementationProvider = injector.root().implementationProvider

    init {
        if (internals != null) {
            internals[BackgroundService::class.java] = injector.internals().backgroundService
        }
    }

    override fun getCommandAPI(): CommandAPI = commandAPI

    override fun getQueryAPI(): QueryAPI = queryAPI

    override fun <T : Port> provideImplementation(klass: Class<T>, implementation: T) {
        implementationProvider.provideImplementation(klass, implementation)
    }
}