package com.github.rskupnik.thestory.shared

import java.util.*

/**
 * Represents a reference to something, interpretation of the reference depends on
 * internal workings of particular modules.
 */
class Reference(val value: String) {

    companion object {
        fun generate(): Reference = Reference(UUID.randomUUID().toString())
        fun copy(ref: Reference): Reference = Reference(ref.value)
    }

    fun clone(): Reference = copy(this)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reference

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}