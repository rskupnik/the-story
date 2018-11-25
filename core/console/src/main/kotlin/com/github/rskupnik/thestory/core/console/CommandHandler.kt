package com.github.rskupnik.thestory.core.console

/**
 * Represents a handler for a specific command
 */
interface CommandHandler {

    fun id(): String

    fun handle(params: Array<String>)
}