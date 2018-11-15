package com.github.rskupnik.thestory.persistence

interface Persister {

    val persistenceKey: String

    fun produceState(): Any?

    fun ingestState(state: Any?)
}