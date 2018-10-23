package com.github.rskupnik.thestory.script.domain

data class Script constructor(
        val elements: Set<Element>,
        val exits: Set<Exit>
)