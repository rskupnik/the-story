package com.github.rskupnik.thestory.background

import com.github.rskupnik.thestory.persistence.Persister

interface BackgroundService : Persister {

    fun setNoBackground()

    fun setNormalMappedBackground(image: String, normalImage: String)
}