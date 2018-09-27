package com.github.rskupnik.thestory.shared

/**
 * This is a self-designed design pattern used to mutate internal objects from the outside.
 *
 * It works by transforming input arguments (passed in constructor) into [MutatorEntry] objects and writing a small
 * piece of logic that does the updating of the actual, hidden, internal object.
 */
abstract class Mutator {

    internal abstract fun <T> mutate(instance: T)

    protected fun <R> set(value: R?): MutatorEntry<R> =
            if (value == null) MutatorEntry.disabled() else MutatorEntry.enabled(value)

    protected fun mutateState(currentState: ExternalState?, additions: Map<String, Any>): ExternalState {
        return if (currentState == null) {
            ExternalState.fromExistingState(additions)
        } else {
            for ((key, value) in additions) {
                currentState[key] = value
            }
            currentState
        }
    }
}