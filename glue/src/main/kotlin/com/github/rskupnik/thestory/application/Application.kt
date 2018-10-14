package com.github.rskupnik.thestory.application

import com.github.rskupnik.thestory.application.internal.APIImpl
import com.github.rskupnik.thestory.application.internal.DaggerInjector
import com.github.rskupnik.thestory.application.internal.Injector

object Application {
    fun init(): API {
        val injector: Injector = DaggerInjector.create()
        return APIImpl(injector)
    }
}