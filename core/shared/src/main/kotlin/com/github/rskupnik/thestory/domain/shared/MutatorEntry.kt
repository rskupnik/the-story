package com.github.rskupnik.thestory.domain.shared

/**
 * Used by [Mutator] classes, it's needed because the [Mutator] needs to differentiate three cases:
 * * Setting a value - [enabled] is `true` and [value] contains the desired value
 * * Ignoring a value - [enabled] is `false` and [value] is `null` (or undefined)
 * * Nullifying a value - [enabled] is `true` and [value] is `null`
 *
 * Companion object's methods [disabled], [enabled] and [nullify] are used to create instances of this class,
 * matching each of those cases.
 */
class MutatorEntry<T> private constructor(
        val enabled: Boolean,
        val value: T?
) {
    companion object {
        fun <T> disabled(): MutatorEntry<T> = MutatorEntry(false, null)
        fun <T> enabled(value: T): MutatorEntry<T> = MutatorEntry(true, value)
        fun <T> nullify(): MutatorEntry<T> = MutatorEntry(true, null)
    }
}