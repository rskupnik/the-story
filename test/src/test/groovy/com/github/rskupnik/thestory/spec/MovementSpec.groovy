package com.github.rskupnik.thestory.spec


import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Direction

class MovementSpec extends AbstractSpec {

    def "move west loads the proper location"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        app.api.commandAPI.initializeGame("demo")

        when:
        app.api.commandAPI.movePlayer(Direction.WEST)

        then:
        1 * app.getCallbackReceiver().onLocationLoaded(_)
    }

    def "move to non-existent location does nothing and does not trigger onLocationLoaded"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        app.api.commandAPI.initializeGame("demo")

        when:
        app.api.commandAPI.movePlayer(Direction.WEST)
        app.api.commandAPI.movePlayer(Direction.WEST)   // This location does not exist

        then:
        noExceptionThrown()
        1 * app.getCallbackReceiver().onLocationLoaded(_)   // Triggered only for the first move
    }
}
