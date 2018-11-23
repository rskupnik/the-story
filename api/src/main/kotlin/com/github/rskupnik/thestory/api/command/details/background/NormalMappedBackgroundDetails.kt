package com.github.rskupnik.thestory.api.command.details.background

/**
 * Details of a normal-mapped background to be set.
 */
data class NormalMappedBackgroundDetails(
        val image: String,
        val normalImage: String
) : BackgroundDetails