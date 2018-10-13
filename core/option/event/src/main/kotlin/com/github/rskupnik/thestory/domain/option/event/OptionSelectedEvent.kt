package com.github.rskupnik.thestory.domain.option.event

import com.github.rskupnik.thestory.event.Event
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

data class OptionSelectedEvent(
        val option: Option,
        val externalData: Map<String, Any>?,
        val context: Context,
        val entityId: EntityId
) : Event