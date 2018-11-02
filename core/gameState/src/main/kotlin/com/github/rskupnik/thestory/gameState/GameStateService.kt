package com.github.rskupnik.thestory.gameState

import com.github.rskupnik.thestory.background.domain.Background

interface GameStateService {

    fun setBackground(background: Background)

    fun getBackground(): Background?
}