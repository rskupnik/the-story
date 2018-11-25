package com.github.rskupnik.thestory.background

import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.Service

/**
 * This service contains logic for manipulating the background.
 */
interface BackgroundService : Service, Persister {

    /**
     * Set a default, black background.
     */
    fun setNoBackground()

    /**
     * Set a normal-mapped background, with the [image] and [normalImage] being used for display and lighting.
     */
    fun setNormalMappedBackground(image: String, normalImage: String)
}