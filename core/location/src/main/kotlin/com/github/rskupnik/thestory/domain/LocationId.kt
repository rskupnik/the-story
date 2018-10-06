package com.github.rskupnik.thestory.domain

data class LocationId(
        val zone: String,
        val x: Int,
        val y: Int
) {
    fun applyDirection(direction: Direction): LocationId = LocationId(
            zone,
            x + (if (direction == Direction.WEST) -1 else if (direction == Direction.EAST) 1 else 0),
            y + (if (direction == Direction.SOUTH) -1 else if (direction == Direction.NORTH) 1 else 0)
    )
}