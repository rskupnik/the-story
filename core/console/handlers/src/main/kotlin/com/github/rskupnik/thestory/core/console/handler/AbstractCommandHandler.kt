package com.github.rskupnik.thestory.core.console.handler

import com.github.rskupnik.thestory.core.console.CommandHandler
import com.github.rskupnik.thestory.core.console.ConsoleService

abstract class AbstractCommandHandler(
        consoleService: ConsoleService
) : CommandHandler {
    init {
        consoleService.register(this)
    }
}