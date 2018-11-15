package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.persistence.Persister

// TODO: Rename to service
interface PlayerFacade : Persister {

    fun setCurrentLocation(locationId: LocationId)

    fun getCurrentLocation(): LocationId
}