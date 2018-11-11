package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.setup.ApplicationContext

class PersistenceSpec extends AbstractSpec {

    def "should save state - basic test"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        app.api.commandAPI.initializeGame("demo")

        when:
        app.api.commandAPI.saveGame()

        then:
        Map<String, ?> savedState = ((InMemoryFileSaver)app.fileSaver).get("demo.sav") as Map<String, ?>
        savedState != null
        savedState.get("objects") != null
        savedState.get("items") != null
    }
}
