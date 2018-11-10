package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.implementations.proxy.ProxyCallbackReceiver
import com.github.rskupnik.thestory.setup.Setup
import com.github.rskupnik.thestory.shared.Direction
import spock.lang.Specification

class MovementSpec extends Specification {

    def "move west loads the proper location"() {
        given:
        def (API api, CallbackReceiver callbackReceiverProxy) = Setup.standardApplication()
        def CallbackReceiver callbackReceiver = Mock()
        ((ProxyCallbackReceiver)callbackReceiverProxy).setActual(callbackReceiver)

        when:
        api.commandAPI.movePlayer(Direction.WEST)

        then:
        1 * callbackReceiver.onLocationLoaded(_)
    }
}
