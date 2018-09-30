package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.shared.Definition

data class Action(
        val id: String,
        val contexts: List<String>,
        val params: Map<String, Any>,
        val conditions: Map<String, Any>
) : Definition