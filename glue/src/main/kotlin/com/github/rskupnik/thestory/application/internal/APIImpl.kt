package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.external.Port

internal class APIImpl(
        injector: Injector,
        internalsContainer: InternalsContainer?
) : API {

    private val queryAPI: QueryAPI = injector.root().queryAPI
    private val commandAPI: CommandAPI = injector.root().commandAPI
    private val implementationProvider = injector.root().implementationProvider

    init {
        internalsContainer?.set(injector.internals())
    }

    override fun getCommandAPI(): CommandAPI = commandAPI

    override fun getQueryAPI(): QueryAPI = queryAPI

    override fun <T : Port> provideImplementation(klass: Class<T>, implementation: T) {
        implementationProvider.provideImplementation(klass, implementation)
    }
}