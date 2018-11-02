package com.github.rskupnik.thestory.background.domain

import com.github.rskupnik.thestory.external.asset.Image

data class NormalMappedBackground(
        private val image: Image,
        private val normalImage: Image,
        private val imageStr: String,
        private val normalImageStr: String
): Background {
    override fun clone(): Background = NormalMappedBackground(image, normalImage, imageStr, normalImageStr)
}