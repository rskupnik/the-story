package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Service
import groovy.util.Proxy
import spock.lang.Specification

abstract class AbstractSpec extends Specification {

    protected def CallbackReceiver mockCallbackReceiver() {
        return Mock(CallbackReceiver)
    }

    protected <T extends Service> Service enableSpy(ApplicationContext app, Class<T> clazz) {
        def spy = Spy(app.getInternalImpl(clazz))
        app.enableSpy(clazz, spy)
        return spy
    }
}
