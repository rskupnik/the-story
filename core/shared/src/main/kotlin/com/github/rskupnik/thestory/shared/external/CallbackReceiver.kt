package com.github.rskupnik.thestory.shared.external

interface CallbackReceiver : Port {

    fun onTest(msg: String)
}