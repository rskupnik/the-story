package com.github.rskupnik.thestory.core.console

/**
 * Handles console commands
 */
interface ConsoleService {

    fun register(handler: CommandHandler)

    fun execute(cmd: String)
}