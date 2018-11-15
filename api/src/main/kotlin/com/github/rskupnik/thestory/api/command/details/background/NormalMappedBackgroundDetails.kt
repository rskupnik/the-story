package com.github.rskupnik.thestory.api.command.details.background

data class NormalMappedBackgroundDetails(
        private val image: String,
        private val normalImage: String
) : BackgroundDetails