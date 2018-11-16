package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.background.BackgroundService
import javax.inject.Inject

internal class Internals
@Inject internal constructor(
        val backgroundService: BackgroundService
)