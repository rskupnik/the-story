package com.github.rskupnik.thestory.script.domain

import com.github.rskupnik.thestory.shared.Direction

enum class Exit {
    NORTH, SOUTH, EAST, WEST;

    companion object {
        fun fromDirection(direction: Direction): Exit = Exit.valueOf(direction.name)
    }
}