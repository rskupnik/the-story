package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import spock.lang.Specification

abstract class AbstractSpec extends Specification {

    def CallbackReceiver mockCallbackReceiver(ApplicationContext app) {
        def CallbackReceiver mock = Mock()
        app.provideCallbackReceiverProxyTarget(mock)
        return mock
    }
}
