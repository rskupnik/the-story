package com.github.rskupnik.thestory.core.console

/**
 * Represents a handler for a specific command
 */
interface CommandHandler {

    /**
     * A String representing the command this handler will handle.
     */
    fun id(): String

    /**
     * Main handling logic resides here.
     */
    fun handle(params: Array<String>)
}