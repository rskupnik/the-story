package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.domain.action.internal.DefaultActionExecutor
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher

object ActionInjectorHandle {

    fun executor(itemService: ItemService, eventDispatcher: EventDispatcher): ActionExecutor = DefaultActionExecutor(
            itemService, eventDispatcher
    )
}