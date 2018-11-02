package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.background.domain.NoBackground

internal object GameState {
    internal var background: Background? = NoBackground()
}