package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.Service

// TODO: Rename to service
interface PlayerFacade : Service, Persister {

    fun setCurrentLocation(locationId: LocationId)

    fun getCurrentLocation(): LocationId
}