package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.persistence.PersistenceService

internal class PersistenceServiceDelegate(
        override var target: PersistenceService
) : PersistenceService by target, ServiceDelegate<PersistenceService>()