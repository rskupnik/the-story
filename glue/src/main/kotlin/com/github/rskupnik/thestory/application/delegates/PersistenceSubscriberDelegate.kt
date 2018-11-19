package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

internal class PersistenceSubscriberDelegate(
        var service: PersistenceSubscriber
) : PersistenceSubscriber by service, ServiceDelegate<PersistenceSubscriber>(service)