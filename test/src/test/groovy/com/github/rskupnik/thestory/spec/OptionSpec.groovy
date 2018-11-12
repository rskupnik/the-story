package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Direction

class OptionSpec extends AbstractSpec {

    def "clicking an object displays a list of options"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.movePlayer(Direction.WEST)
        app.api.commandAPI.clickObject("desk_0_0")

        then:
        1 * app.callbackReceiver.onDisplayOptions(_)
    }
}
