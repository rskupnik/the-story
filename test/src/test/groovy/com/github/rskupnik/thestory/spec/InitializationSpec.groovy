package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.setup.ApplicationContext

class InitializationSpec extends AbstractSpec {

    def "should initialize game without errors"() {
        given:
        def app = ApplicationContext.standardApplication()

        when:
        app.api.commandAPI.initializeGame("demo")

        then:
        noExceptionThrown()
    }
}
