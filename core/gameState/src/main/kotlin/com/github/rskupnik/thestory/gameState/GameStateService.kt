package com.github.rskupnik.thestory.gameState

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.persistence.Persister

interface GameStateService : Persister {

    fun setBackground(background: Background?)

    fun getBackground(): Background?
}