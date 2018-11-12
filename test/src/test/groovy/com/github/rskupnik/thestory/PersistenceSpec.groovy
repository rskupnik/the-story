package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileSaver
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
        def verifier = new SavedStateVerifier(app.fileSaver, "demo.sav")
        verifier.verifyExists()
        verifier.verifyItemAmount(1)
        verifier.verifyItem(0, "torch", [toggled: false])
    }

    // TODO: Test - click object displays list of options

    // TODO: Pull this class out
    private class SavedStateVerifier {
        private final Map<String, ?> state

        private SavedStateVerifier(FileSaver fileSaver, String filename) {
            this.state = ((InMemoryFileSaver)fileSaver).get(filename) as Map<String, ?>
        }

        private boolean verifyExists() {
            return state != null
        }

        private boolean verifyItemAmount(int amount) {
            List<Map<String, ?>> items = state.get("items")
            return items != null && items.size() == amount
        }

        private boolean verifyItem(int index, String blueprint, Map<String, ?> externalState) {
            List<Map<String, ?>> items = state.get("items")
            Map<String, ?> state = items.get(index)
            return ((String)state.get("id")).length() > 10 && ((String)state.get("blueprint")).equals(blueprint) &&
                    verifyState((Map<String, ?>)state.get("externalState"), externalState)
        }

        private boolean verifyState(Map<String, ?> expected, Map<String, ?> given) {
            for (Map.Entry<String, ?> entry : given) {
                if (expected.get(entry.key) == null || !entry.value.equals(expected.get(entry.key))) {
                    return false
                }
            }
            return true
        }
    }
}
