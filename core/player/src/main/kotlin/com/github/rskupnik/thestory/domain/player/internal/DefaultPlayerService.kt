package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade

internal open class DefaultPlayerService : PlayerFacade {
    override val persistenceKey = "player"

    private val player = Player()

    override fun setCurrentLocation(locationId: LocationId) {
        player.currentLocation = locationId
    }

    override fun getCurrentLocation(): LocationId = player.currentLocation

    override fun produceState(): Any? = mapOf(
            "location" to player.currentLocation.toPersistableState()
    )

    override fun ingestState(state: Any?) {
        val stateMap = (state ?: return) as Map<String, Any?>
        setCurrentLocation(LocationId(
                (stateMap["zone"] ?: return) as String,
                (stateMap["x"] ?: return) as Int,
                (stateMap["y"] ?: return) as Int
        ))
    }
}