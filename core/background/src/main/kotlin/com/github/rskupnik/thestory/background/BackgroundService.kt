package com.github.rskupnik.thestory.background

import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.Service

interface BackgroundService : Service, Persister {

    fun setNoBackground()

    fun setNormalMappedBackground(image: String, normalImage: String)
}