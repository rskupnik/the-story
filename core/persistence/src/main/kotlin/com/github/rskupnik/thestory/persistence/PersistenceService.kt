package com.github.rskupnik.thestory.persistence

import com.github.rskupnik.thestory.shared.Service

interface PersistenceService : Service {

    fun save()

    fun readState(filename: String): Map<String, Any?>

    fun loadState(state: Map<String, Any?>)
}