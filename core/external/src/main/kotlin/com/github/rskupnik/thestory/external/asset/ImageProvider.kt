package com.github.rskupnik.thestory.external.asset

interface ImageProvider {
    fun getImage(id: String): Image?
}