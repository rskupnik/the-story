package com.github.rskupnik.thestory.core.console.handler

import com.github.rskupnik.thestory.core.console.ConsoleService

class PrintCommandHandler(
        consoleService: ConsoleService
) : AbstractCommandHandler(consoleService) {

    override fun id(): String = "print"

    override fun handle(params: Array<String>) {
        println("HELLO CONSOLE COMMAND")
    }
}