package com.github.rskupnik.thestory.shared.external

import com.github.rskupnik.thestory.shared.external.dto.OptionLabel

interface CallbackReceiver : Port {

    fun onDisplayOptions(options: List<OptionLabel>)
}