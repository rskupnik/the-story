package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.script.domain.Script

internal class CallbackReceiverDelegate : ImplementationDelegate<CallbackReceiver>(), CallbackReceiver {
    override fun onItemImageChanged(item: ItemView) = getImplementation().onItemImageChanged(item)

    override fun onItemRemoved(item: ItemView) = getImplementation().onItemRemoved(item)

    override fun onNewItemFound(item: ItemView) = getImplementation().onNewItemFound(item)

    override fun onLocationLoaded(script: Script) = getImplementation().onLocationLoaded(script)

    override fun onDisplayOptions(options: List<OptionLabel>) = getImplementation().onDisplayOptions(options)

    override fun onItemEquipped(item: ItemView) = getImplementation().onItemEquipped(item)
}