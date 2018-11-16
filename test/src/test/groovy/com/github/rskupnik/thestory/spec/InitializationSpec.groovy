package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.proxy.BackgroundServiceProxy
import com.github.rskupnik.thestory.setup.ApplicationContext
import groovy.util.Proxy
import spock.lang.Specification

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
