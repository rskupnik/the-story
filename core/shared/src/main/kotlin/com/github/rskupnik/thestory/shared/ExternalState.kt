package com.github.rskupnik.thestory.shared

/**
 * Represents an external state of an object in the form of arbitrary [String] - [Any] pairs.
 *
 * It is mainly used to hold a state in a very loose form.
 */
class ExternalState() : HashMap<String, Any>() {

    companion object {
        fun fromExistingState(state: Map<String, Any>): ExternalState = ExternalState(state)
    }

    private constructor(init: Map<String, Any>?) : this() {
        val map = requireNotNull(init)
        clear()
        for ((key, value) in map) {
            put(key, value)
        }
    }
}