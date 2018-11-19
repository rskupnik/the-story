package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.persistence.PersistenceService

internal class PersistenceServiceDelegate(
        var service: PersistenceService
) : PersistenceService by service, ServiceDelegate<PersistenceService>(service)