package com.github.rskupnik.thestory.application

import com.github.rskupnik.thestory.application.internal.DaggerInjector
import com.github.rskupnik.thestory.application.internal.APIImpl
import com.github.rskupnik.thestory.application.internal.Injector
import com.github.rskupnik.thestory.application.internal.InternalsContainer

object Application {
    fun init(internalsContainer: InternalsContainer? = null): API {
        val injector: Injector = DaggerInjector.create()
        return APIImpl(injector, internalsContainer)
    }
}