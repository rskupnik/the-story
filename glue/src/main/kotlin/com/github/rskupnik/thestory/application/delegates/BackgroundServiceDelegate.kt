package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.background.BackgroundService

internal class BackgroundServiceDelegate(
        override var target: BackgroundService
) : BackgroundService by target, ServiceDelegate<BackgroundService>()