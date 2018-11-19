package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

internal class PersistenceSubscriberDelegate(
        override var target: PersistenceSubscriber
) : PersistenceSubscriber by target, ServiceDelegate<PersistenceSubscriber>()