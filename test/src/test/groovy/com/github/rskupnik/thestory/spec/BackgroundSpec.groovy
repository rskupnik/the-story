package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.api.command.details.background.NormalMappedBackgroundDetails
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.setup.ApplicationContext

class BackgroundSpec extends AbstractSpec {

    def "should set background: no background"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, BackgroundService.class)

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.setBackground(null)

        then:
        1 * ((BackgroundService)spy).setNoBackground()
        0 * ((BackgroundService)spy).setNormalMappedBackground(_, _)
    }

    def "should set background: normal-mapped background"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, BackgroundService.class)
        def image = "image"
        def normalImage = "normalImage"

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.setBackground(new NormalMappedBackgroundDetails(image, normalImage))

        then:
        0 * ((BackgroundService)spy).setNoBackground()
        1 * ((BackgroundService)spy).setNormalMappedBackground(image, normalImage)
    }
}
