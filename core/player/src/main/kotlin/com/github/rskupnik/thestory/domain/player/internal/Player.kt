package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId

internal object Player {
    internal var currentLocation: LocationId = LocationId("default", 0, 0)
}