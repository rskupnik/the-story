package com.github.rskupnik.thestory.background.domain

class NoBackground: Background {
    override fun clone(): Background = NoBackground()
}