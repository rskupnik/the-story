package com.github.rskupnik.thestory.core.console.handler

import com.github.rskupnik.thestory.core.console.ConsoleService

object ConsoleCommandInjectorHandle {

    fun printCommandHandler(consoleService: ConsoleService): PrintCommandHandler = PrintCommandHandler(consoleService)
}