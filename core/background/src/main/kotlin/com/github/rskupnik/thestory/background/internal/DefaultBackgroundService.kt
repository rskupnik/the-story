package com.github.rskupnik.thestory.background.internal

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.background.domain.NoBackground
import com.github.rskupnik.thestory.background.domain.NormalMappedBackground
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.shared.Reference

internal class DefaultBackgroundService(
        private val moduleService: ModuleService,
        private val callbackReceiver: CallbackReceiver
): BackgroundService {
    override val persistenceKey = "background"

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

    override fun produceState(): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ingestState(state: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}