package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.application.API

internal class APIImpl(private val injector: Injector) : API {

    private val queryAPI: QueryAPI = injector.root().queryAPI
    private val commandAPI: CommandAPI = injector.root().commandAPI

    override fun getCommandAPI(): CommandAPI = commandAPI

    override fun getQueryAPI(): QueryAPI = queryAPI
}