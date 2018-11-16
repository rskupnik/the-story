package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import groovy.util.Proxy
import spock.lang.Specification

abstract class AbstractSpec extends Specification {

    protected def CallbackReceiver mockCallbackReceiver() {
        return Mock(CallbackReceiver)
    }

    protected <T> Proxy enableSpy(ApplicationContext app, Class<T> clazz) {
        def proxy = new Proxy().wrap(app.getInternalImpl(clazz))
        def spy = Spy(proxy)
        app.enableSpy(spy)
        return spy
    }
}
