package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext

class BackgroundSpec extends AbstractSpec {

    // TODO: Test setting background - need access to internal classes for tests first
    def "should set background"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, BackgroundService.class)

        when:
        app.api.commandAPI.setBackground(null)

        then:
        1 * ((BackgroundService)spy).setNoBackground()
    }
}
