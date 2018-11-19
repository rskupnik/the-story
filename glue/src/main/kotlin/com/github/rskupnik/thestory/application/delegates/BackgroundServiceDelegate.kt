package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.background.BackgroundService

internal class BackgroundServiceDelegate(
        override var delegatee: BackgroundService
) : RuntimeDelegate<BackgroundService>(), BackgroundService {
    override fun setNoBackground() = delegatee.setNoBackground()

    override fun setNormalMappedBackground(image: String, normalImage: String) = get().setNormalMappedBackground(image, normalImage)

    override val persistenceKey: String = get().persistenceKey

    override fun produceState(): Any? = get().produceState()

    override fun ingestState(state: Any?) = get().ingestState(state)
}