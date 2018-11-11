package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade

internal class DefaultPlayerService : PlayerFacade {

    private val player = Player()

    override fun setCurrentLocation(locationId: LocationId) {
        player.currentLocation = locationId
    }

    override fun getCurrentLocation(): LocationId = player.currentLocation
}