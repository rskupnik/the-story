package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment

object ActionHandlerInjectorHandle {

    fun equipItemActionHandler(actionExecutor: ActionExecutor, equipment: Equipment): EquipItemActionHandler = EquipItemActionHandler(
            actionExecutor, equipment
    )
}