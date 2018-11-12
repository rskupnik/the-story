package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import spock.lang.Specification

abstract class AbstractSpec extends Specification {

    def CallbackReceiver mockCallbackReceiver() {
        return Mock(CallbackReceiver)
    }
}
