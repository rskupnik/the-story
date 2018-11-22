package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.gamestate.domain.GamePhase
import com.github.rskupnik.thestory.setup.ApplicationContext

class InitializationSpec extends AbstractSpec {

    def "should initialize game without errors"() {
        given:
        def app = ApplicationContext.standardApplication()

        when:
        app.api.commandAPI.initializeGame("demo")

        then:
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should only initialize once"() {
        given:
        def app = ApplicationContext.standardApplication()

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.initializeGame("demo")

        then:
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }
}
