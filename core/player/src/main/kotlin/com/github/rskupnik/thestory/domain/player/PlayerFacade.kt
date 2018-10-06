package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.LocationId

interface PlayerFacade {

    fun setCurrentLocation(locationId: LocationId)

    fun getCurrentLocation(): LocationId
}