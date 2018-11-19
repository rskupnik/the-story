package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.background.BackgroundService

internal class BackgroundServiceDelegate(var backgroundService: BackgroundService)
    : BackgroundService by backgroundService, ServiceDelegate<BackgroundService>(backgroundService)