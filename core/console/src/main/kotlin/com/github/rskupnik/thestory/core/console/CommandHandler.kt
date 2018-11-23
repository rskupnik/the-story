package com.github.rskupnik.thestory.core.console

interface CommandHandler {

    fun id(): String

    fun handle(params: Array<String>)
}