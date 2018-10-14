package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.CallbackReceiver

internal class CallbackReceiverDelegate : ImplementationDelegate<CallbackReceiver>(), CallbackReceiver {
    override fun onTest(msg: String) = getImplementation()?.onTest(msg) ?: throw IllegalStateException("Implementation not provided")
}