package com.github.rskupnik.thestory.background.internal

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.background.domain.NoBackground
import com.github.rskupnik.thestory.background.domain.NormalMappedBackground
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.gameState.GameStateService
import com.github.rskupnik.thestory.shared.Reference

internal class DefaultBackgroundService(
        private val gameStateService: GameStateService,
        private val moduleService: ModuleService,
        private val callbackReceiver: CallbackReceiver
): BackgroundService {

    private var background: Background? = null

    override fun setNoBackground() {
        this.background = null
        callbackReceiver.onBackgroundChanged(NoBackground())
    }

    override fun setNormalMappedBackground(image: String, normalImage: String) {
        val img = requireNotNull(moduleService.getImage(Reference.to(image)))
        val normalImg = requireNotNull(moduleService.getImage(Reference.to(normalImage)))

        val newBackground = NormalMappedBackground(img, normalImg, image, normalImage)
        this.background = newBackground
        callbackReceiver.onBackgroundChanged(newBackground)
    }
}