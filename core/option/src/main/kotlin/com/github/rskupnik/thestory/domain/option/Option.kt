package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.shared.Definition

data class Option(
        val id: String,
        //val actions: List<Action>,
        val conditions: Map<String, Any>,
        val contexts: List<String>
) : Definition {
}