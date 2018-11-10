package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.domain.LocationId
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
}
