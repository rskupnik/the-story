package com.github.rskupnik.thestory.background.domain

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.persistence.Persistable

data class NormalMappedBackground(
        val image: Image,
        val normalImage: Image,
        private val imageStr: String,
        private val normalImageStr: String
): Background {

    override fun toPersistableState(): Map<String, Any?> = mapOf(
            "type" to "NORMAL_MAPPED",
            "image" to imageStr,
            "normalImage" to normalImageStr
    )

    override fun clone(): Background = NormalMappedBackground(image, normalImage, imageStr, normalImageStr)
}