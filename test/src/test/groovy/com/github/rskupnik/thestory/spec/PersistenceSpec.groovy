package com.github.rskupnik.thestory.spec

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Direction
import com.github.rskupnik.thestory.shared.entity.EntityType
import com.github.rskupnik.thestory.verification.SavedStateVerifier

class PersistenceSpec extends AbstractSpec {

    def "should save empty state"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.saveGame()

        then:
        Map<String, ?> savedState = ((InMemoryFileSaver)app.fileSaver).get("demo.sav") as Map<String, ?>
        savedState != null
        savedState.get("objects") != null
        savedState.get("items") != null
    }

    def "should save state - single item in inventory"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.movePlayer(Direction.WEST)
        app.api.commandAPI.clickObject("desk_0_0")
        app.api.commandAPI.selectOption("desk_0_0", EntityType.OBJECT, "search", null)
        app.api.commandAPI.saveGame()

        then:
        def verifier = new SavedStateVerifier(app.fileSaver, "demo.sav")
        verifier.verifyExists()
        verifier.verifyItemAmount(1)
        verifier.verifyItem(0, "torch", [toggled: false])
    }

    // TODO: Basic load game test - see if initializes after loadGame()
    def "should initialize module upon load"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.loadGame("empty.sav")

        then:
        true
        // TODO: Create the .sav file for this test
        // TODO: Make sure module is initialized when the sav file is loaded
    }

    // TODO: Work on saving player data and game state, such as background, etc.

    // TODO: More advanced saved & load tests
}
