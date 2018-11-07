package com.github.rskupnik.thestory.persistence

interface PersistenceService {

    fun save()

    fun readState(filename: String): Map<String, Any?>

    fun loadState(state: Map<String, Any?>)
}