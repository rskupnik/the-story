package com.github.rskupnik.thestory.domain.player.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

internal class DefaultPlayerService(
        persistenceSubscriber: PersistenceSubscriber
) : PlayerFacade {
    override val persistenceKey = "player"

    private val player = Player()

    init {
        persistenceSubscriber.subscribe(this)
    }

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