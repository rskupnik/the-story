package com.github.rskupnik.thestory.domain.shared

enum class Context {
    INVENTORY, EQUIPMENT;

    companion object {
        fun fromString(str: String): Context = Context.valueOf(str.toUpperCase())
    }
}