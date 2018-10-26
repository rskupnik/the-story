package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.Port

internal open class ImplementationDelegate<T : Port> {
    private var implementation: T? = null

    fun getImplementation(): T = implementation ?: throw IllegalStateException("Implementation not provided")

    fun setImplementation(implementation: T) {
        this.implementation = implementation
    }
}