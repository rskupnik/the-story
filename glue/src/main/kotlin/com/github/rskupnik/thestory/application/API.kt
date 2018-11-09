package com.github.rskupnik.thestory.application

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.external.Port
import kotlin.reflect.KClass

interface API {
    fun getCommandAPI(): CommandAPI
    fun getQueryAPI(): QueryAPI

    fun <T : Port> provideImplementation(klass: Class<T>, implementation: T)
}