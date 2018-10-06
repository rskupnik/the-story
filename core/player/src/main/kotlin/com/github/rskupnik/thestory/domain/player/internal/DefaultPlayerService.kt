package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade

internal class DefaultPlayerService : PlayerFacade {

    override fun setCurrentLocation(locationId: LocationId) {
        Player.currentLocation = locationId
    }

    override fun getCurrentLocation(): LocationId = Player.currentLocation
}