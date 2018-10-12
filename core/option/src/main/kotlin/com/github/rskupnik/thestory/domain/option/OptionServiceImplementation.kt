package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.domain.option.event.OptionSelectedEvent
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

internal class OptionServiceImplementation(
        private val eventDispatcher: EventDispatcher
) : OptionService {

    override fun execute(option: Option, externalData: Map<String, Any>, context: Context, entityId: EntityId) {
        eventDispatcher.dispatch(OptionSelectedEvent(option, externalData, context, entityId))
    }
}