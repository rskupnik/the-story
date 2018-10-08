package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import javax.inject.Inject

internal class Root
@Inject internal constructor(
        val queryAPI: QueryAPI,
        val commandAPI: CommandAPI
)