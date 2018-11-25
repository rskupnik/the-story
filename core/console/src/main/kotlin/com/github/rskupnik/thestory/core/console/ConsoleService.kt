package com.github.rskupnik.thestory.core.console

/**
 * Handles console commands.
 */
interface ConsoleService {

    /**
     * Register a [CommandHandler].
     */
    fun register(handler: CommandHandler)

    /**
     * Execute a command.
     */
    fun execute(cmd: String)
}