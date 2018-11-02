package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId

class FindItemActionHandler(
        actionExecutor: ActionExecutor,
        private val itemService: ItemService,
        private val inventory: Inventory,
        private val callbackReceiver: CallbackReceiver
) : AbstractActionHandler(actionExecutor) {
    override fun id(): String = "findItem"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val blueprintId: String = requireNotNull(action.params["id"] as String)

        // Instantiate the item
        val itemRef = requireNotNull(itemService.instantiate(Reference.to(blueprintId)))

        // Store the item in Inventory
        inventory.put(itemRef)

        // Produce a view of the item
        val itemView = requireNotNull(itemService.getItemView(itemRef))

        // Trigger onNewItemFound with the produced view of the item
        callbackReceiver.onNewItemFound(itemView)
    }
}