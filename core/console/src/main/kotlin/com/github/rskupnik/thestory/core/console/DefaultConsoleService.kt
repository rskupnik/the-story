package com.github.rskupnik.thestory.core.console

internal class DefaultConsoleService : ConsoleService {

    private val handlers: MutableMap<String, CommandHandler> = HashMap()

    override fun register(handler: CommandHandler) {
        handlers[handler.id()] = handler
    }

    override fun execute(id: String, params: Array<Any>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}