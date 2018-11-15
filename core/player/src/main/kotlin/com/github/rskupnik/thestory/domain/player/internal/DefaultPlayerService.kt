package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade

internal class DefaultPlayerService : PlayerFacade {
    override val persistenceKey = "player"

    private val player = Player()

    override fun setCurrentLocation(locationId: LocationId) {
        player.currentLocation = locationId
    }

    override fun getCurrentLocation(): LocationId = player.currentLocation

    override fun produceState(): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ingestState(state: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}