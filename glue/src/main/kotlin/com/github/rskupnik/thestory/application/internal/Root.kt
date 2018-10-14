package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.application.adapter.ImplementationProvider
import javax.inject.Inject

internal class Root
@Inject internal constructor(
        val queryAPI: QueryAPI,
        val commandAPI: CommandAPI,
        val implementationProvider: ImplementationProvider
)