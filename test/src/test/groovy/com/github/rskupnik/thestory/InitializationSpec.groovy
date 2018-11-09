package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.setup.Setup
import spock.lang.Specification

class InitializationSpec extends Specification {

    def "should initialize game without errors"() {
        given:
        final API api = Setup.standardApplication()

        when:
        api.commandAPI.initializeGame("demo")

        then:
        noExceptionThrown()
    }
}
