package com.github.rskupnik.thestory.external

import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.script.domain.Script

interface CallbackReceiver : Port {

    fun onLocationLoaded(script: Script)

    fun onDisplayOptions(options: List<OptionLabel>)
}