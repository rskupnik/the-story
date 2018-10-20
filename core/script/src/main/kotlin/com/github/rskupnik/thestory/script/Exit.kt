package com.github.rskupnik.thestory.script

import com.github.rskupnik.thestory.domain.Direction

enum class Exit {
    NORTH, SOUTH, EAST, WEST;

    companion object {
        fun fromDirection(direction: Direction): Exit = Exit.valueOf(direction.name)
    }
}