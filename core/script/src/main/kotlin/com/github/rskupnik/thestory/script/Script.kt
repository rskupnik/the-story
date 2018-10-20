package com.github.rskupnik.thestory.script

data class Script internal constructor(
        val elements: Set<Element>,
        val exits: Set<Exit>
)