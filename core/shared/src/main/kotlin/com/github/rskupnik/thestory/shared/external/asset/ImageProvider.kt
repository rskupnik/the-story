package com.github.rskupnik.thestory.shared.external.asset

interface ImageProvider {
    fun getImage(id: String): Image?
}