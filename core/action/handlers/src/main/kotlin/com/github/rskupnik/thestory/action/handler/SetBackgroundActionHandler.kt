package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.shared.entity.EntityId

class SetBackgroundActionHandler(
        actionExecutor: ActionExecutor,
        private val backgroundService: BackgroundService
): AbstractActionHandler(actionExecutor) {
    override fun id(): String = "setBackground"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val type = requireNotNull(action.params?.get("type"))
        when(type) {
            "none" -> backgroundService.setNoBackground()
            "normalMapped" -> backgroundService.setNormalMappedBackground(
                    requireNotNull(action.params?.get("img") as String),
                    requireNotNull(action.params?.get("normalImg") as String)
            )
        }
    }
}