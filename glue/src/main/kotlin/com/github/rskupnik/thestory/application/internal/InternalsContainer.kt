package com.github.rskupnik.thestory.application.internal

class InternalsContainer {
    private var internals: Internals? = null

    internal fun set(internals: Internals) {
        this.internals = internals
    }

    fun get(): Internals? = internals
}