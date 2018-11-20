package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade

internal class PlayerServiceDelegate(
        override var delegatee: PlayerFacade
) : RuntimeDelegate<PlayerFacade>(), PlayerFacade {

    override fun setCurrentLocation(locationId: LocationId) = get().setCurrentLocation(locationId)

    override fun getCurrentLocation(): LocationId = get().getCurrentLocation()

    override val persistenceKey: String = get().persistenceKey

    override fun produceState(): Any? = get().produceState()

    override fun ingestState(state: Any?) = get().ingestState(state)
}