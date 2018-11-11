package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.setup.ApplicationContext
import com.github.rskupnik.thestory.shared.Direction
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.entity.EntityType

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

    def "should save state - single item in inventory"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.movePlayer(Direction.WEST)
        app.api.commandAPI.clickObject("desk_0_0")
        app.api.commandAPI.selectOption("desk_0_0", EntityType.OBJECT, "search", null)

        when:
        app.api.commandAPI.saveGame()

        then:
        // TODO: Pull out verification code to a separate class?
        Map<String, ?> savedState = ((InMemoryFileSaver)app.fileSaver).get("demo.sav") as Map<String, ?>
        savedState != null
        List<Map<String, ?>> itemsState = savedState.get("items")
        itemsState != null
        itemsState.size() == 1
        Map<String, ?> item = itemsState.get(0)
        item != null
        item.get("id") != null
        ((String)item.get("id")).length() > 10
        item.get("blueprint") != null
        ((String)item.get("blueprint")).equals("torch")
        item.get("externalState") != null
        ((ExternalState)item.get("externalState")).size() == 1
        ((ExternalState)item.get("externalState")).get("toggled") == false
        item.get("currentImage") == null
        item.get("placement") == null
    }

    // TODO: Test - click object displays list of options
}
