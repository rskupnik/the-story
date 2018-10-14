package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.player.internal.DefaultPlayerService

object PlayerInjectorHandle {
    fun service(): PlayerFacade = DefaultPlayerService()
}