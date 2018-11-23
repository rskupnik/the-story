package com.github.rskupnik.thestory.core.console

interface ConsoleService {

    fun register(handler: CommandHandler)

    fun execute(id: String, params: Array<Any>?)
}