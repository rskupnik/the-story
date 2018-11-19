package com.github.rskupnik.thestory.application.delegates

internal abstract class RuntimeDelegate<T> {
    protected abstract var delegatee: T

    internal fun get(): T = delegatee

    internal fun set(delegatee: T) {
        this.delegatee = delegatee
    }
}