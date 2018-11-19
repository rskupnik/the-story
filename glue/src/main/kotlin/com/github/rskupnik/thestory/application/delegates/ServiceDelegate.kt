package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.shared.Service

internal abstract class ServiceDelegate<T : Service>(
        var target: T
) : Service