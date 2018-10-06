package com.github.rskupnik.thestory.domain

/**
 * A ValueObject that makes it easy to pass around location identifiers, as locations
 * are identified by the zone they're in and an a pair of x + y coordinates.
 */
data class LocationId(
        val zone: String,
        val x: Int,
        val y: Int
) {

    /**
     * Produce a new [LocationId] pointing to a location relative to this location.
     *
     * @param direction the [Direction] to be applied
     * @return a [LocationId] being the result of applying [direction] to
     * this location
     */
    fun applyDirection(direction: Direction): LocationId = LocationId(
            zone,
            x + (if (direction == Direction.WEST) -1 else if (direction == Direction.EAST) 1 else 0),
            y + (if (direction == Direction.SOUTH) -1 else if (direction == Direction.NORTH) 1 else 0)
    )
}