package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.gamestate.domain.GamePhase
import com.github.rskupnik.thestory.setup.ApplicationContext

class InitializationSpec extends AbstractSpec {

    def "should initialize game without errors"() {
        given:
        def app = ApplicationContext.standardApplication()
        def spy = enableSpy(app, ModuleService.class)

        when:
        app.api.commandAPI.initializeGame("demo")

        then:
        1 * ((ModuleService)spy).load("demo")
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should only initialize once"() {
        given:
        def app = ApplicationContext.standardApplication()
        def spy = enableSpy(app, ModuleService.class)

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.initializeGame("demo")

        then:
        1 * ((ModuleService)spy).load("demo")   // Only once
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }
}
