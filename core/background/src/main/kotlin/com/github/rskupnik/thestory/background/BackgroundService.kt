package com.github.rskupnik.thestory.background

interface BackgroundService {

    fun setNoBackground()

    fun setNormalMappedBackground(image: String, normalImage: String)
}