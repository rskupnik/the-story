package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.background.BackgroundService

class BackgroundServiceDelegate(var backgroundService: BackgroundService) : BackgroundService by backgroundService