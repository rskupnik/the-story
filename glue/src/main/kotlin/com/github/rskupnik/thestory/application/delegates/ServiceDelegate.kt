package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.shared.Service

class ServiceDelegate<T : Service>(
        var target: T
) : Service by target