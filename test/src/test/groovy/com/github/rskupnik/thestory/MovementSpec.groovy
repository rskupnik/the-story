package com.github.rskupnik.thestory


import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Direction

class MovementSpec extends AbstractSpec {

    def "move west loads the proper location"() {
        given:
        def app = ApplicationContext.standardApplication()
        def callbackReceiver = mockCallbackReceiver(app)

        when:
        app.api.commandAPI.movePlayer(Direction.WEST)

        then:
        1 * callbackReceiver.onLocationLoaded(_)
    }
}
