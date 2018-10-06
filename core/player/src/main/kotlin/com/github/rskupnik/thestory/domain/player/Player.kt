package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.LocationId

internal object Player {
    internal val currentLocation: LocationId = LocationId("default", 0, 0)
}