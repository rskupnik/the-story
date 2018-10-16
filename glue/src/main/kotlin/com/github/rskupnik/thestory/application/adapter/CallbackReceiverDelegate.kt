package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.CallbackReceiver
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel

internal class CallbackReceiverDelegate : ImplementationDelegate<CallbackReceiver>(), CallbackReceiver {

    override fun onDisplayOptions(options: List<OptionLabel>) = getImplementation()?.onDisplayOptions(options) ?: throw IllegalStateException("Implementation not provided")
}