package com.github.rskupnik.thestory.spec


import com.github.rskupnik.thestory.setup.ApplicationContext
import spock.lang.Specification

class InitializationSpec extends Specification {

    def "should initialize game without errors"() {
        given:
        def app = ApplicationContext.standardApplication()

        when:
        app.api.commandAPI.initializeGame("demo")

        then:
        noExceptionThrown()
    }
}
