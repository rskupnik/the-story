package com.github.rskupnik.thestory.core.console

internal class DefaultConsoleService : ConsoleService {

    private val handlers: MutableMap<String, CommandHandler> = HashMap()

    override fun register(handler: CommandHandler) {
        handlers[handler.id()] = handler
    }

    override fun execute(cmd: String) {
        val (id, params) = parse(cmd)
        handlers[id]?.handle(params)
    }

    private fun parse(cmd: String): Pair<String, Array<String>> {
        val temp = cmd.split(" ")
        return Pair(temp[0], temp.subList(1, temp.size).toTypedArray())
    }
}