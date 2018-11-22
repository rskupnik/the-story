package com.github.rskupnik.thestory.spec


import com.github.rskupnik.thestory.api.command.details.background.NormalMappedBackgroundDetails
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.gamestate.domain.GamePhase
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.persistence.PersistenceService
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

    def "should save state - background"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.setBackground(new NormalMappedBackgroundDetails("wall", "wall-normal"))
        app.api.commandAPI.saveGame()

        then:
        def verifier = new SavedStateVerifier(app.fileSaver, "demo.sav")
        verifier.verifyNormalMappedBackground("wall", "wall-normal")
    }

    def "should save state - player data"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.initializeGame("demo")
        app.api.commandAPI.movePlayer(Direction.WEST)
        app.api.commandAPI.saveGame()

        then:
        def verifier = new SavedStateVerifier(app.fileSaver, "demo.sav")
        verifier.verifyPlayerLocation("default", -1, 0)
    }

    def "should initialize module upon load"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))

        when:
        app.api.commandAPI.loadGame("saves/empty.sav")

        then:
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should load state"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, PersistenceService.class)

        when:
        app.api.commandAPI.loadGame("saves/background.sav")

        then:
        1 * ((PersistenceService)spy).loadState(_)
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should load state - background"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, BackgroundService.class)

        when:
        app.api.commandAPI.loadGame("saves/background.sav")

        then:
        1 * ((BackgroundService)spy).ingestState(_)
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should load player location"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, PlayerFacade.class)

        when:
        app.api.commandAPI.loadGame("saves/player.sav")

        then:
        1 * ((PlayerFacade)spy).ingestState(["location": ["zone": "demo", "x": 0, "y": 0]])
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }

    def "should only load game state once"() {
        given:
        def app = ApplicationContext.standardApplication(Mock(CallbackReceiver))
        def spy = enableSpy(app, PersistenceService.class)

        when:
        app.api.commandAPI.loadGame("saves/empty.sav")
        app.api.commandAPI.loadGame("saves/empty.sav")  // This one should just return at the beginning

        then:
        // Persistence should only be triggered once
        1 * ((PersistenceService)spy).readState(_)
        1 * ((PersistenceService)spy).loadState(_)
        app.api.queryAPI.getCurrentGamePhase() == GamePhase.RUNNING
    }
}
