package com.github.rskupnik.thestory.application.delegates

internal abstract class RuntimeDelegate<T> {
    protected abstract var delegatee: T

    protected fun get(): T = delegatee

    protected fun set(delegatee: T) {
        this.delegatee = delegatee
    }
}