package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.shared.entity.EntityId

class EquipItemActionHandler(
        actionExecutor: ActionExecutor,
        private val equipment: Equipment
) : AbstractActionHandler(actionExecutor) {

    init {
        println("EQUIP ITEM ACTION HANDLER INITIALIZED")
    }

    override fun id() = "equipItem"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        println("EQUIP ITEM!")
    }
}