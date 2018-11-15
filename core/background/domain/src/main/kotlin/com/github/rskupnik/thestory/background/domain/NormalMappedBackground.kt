package com.github.rskupnik.thestory.background.domain

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.persistence.Persistable

data class NormalMappedBackground(
        private val image: Image,
        private val normalImage: Image,
        private val imageStr: String,
        private val normalImageStr: String
): Background, Persistable {

    override fun toPersistableState(): Map<String, Any?> = mapOf(
            "type" to "NORMAL_MAPPED",
            "image" to imageStr,
            "normalImage" to normalImageStr
    )

    override fun clone(): Background = NormalMappedBackground(image, normalImage, imageStr, normalImageStr)
}