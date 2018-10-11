package com.github.rskupnik.thestory.application

import com.github.rskupnik.thestory.application.internal.Injector

object Application {
    fun init() {
        val injector: Injector = DaggerInjector.create()
    }
}