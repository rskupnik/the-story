package com.github.rskupnik.thestory.main

import com.github.rskupnik.thestory.application.Application

fun main(args: Array<String>) {
    // For ad-hoc testing
    val api = Application.init()
    val availableModules = api.getQueryAPI().getAvailableModules()
    availableModules.availableModules.forEach(::println)
}