package com.github.rskupnik.thestory.persistence

interface Persister {

    val persistenceKey: String

    fun produceState(): List<Map<String, Any>>

    fun loadState(state: List<Map<String, Any>>)
}