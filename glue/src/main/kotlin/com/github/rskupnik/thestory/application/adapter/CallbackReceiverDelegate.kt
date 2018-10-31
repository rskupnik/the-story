package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.script.domain.Script

internal class CallbackReceiverDelegate : ImplementationDelegate<CallbackReceiver>(), CallbackReceiver {

    override fun onLocationLoaded(script: Script) = getImplementation().onLocationLoaded(script)

    override fun onDisplayOptions(options: List<OptionLabel>) = getImplementation().onDisplayOptions(options)

    override fun onItemEquipped(item: ItemView) = getImplementation().onItemEquipped(item)
}