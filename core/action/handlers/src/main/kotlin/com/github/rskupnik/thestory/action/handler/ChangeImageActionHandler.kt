package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId

class ChangeImageActionHandler(
        actionExecutor: ActionExecutor,
        private val itemService: ItemService,
        private val callbackReceiver: CallbackReceiver
): AbstractActionHandler(actionExecutor) {
    override fun id(): String = "changeImage"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val entity: Reference = Reference.to(requireNotNull(entityId).id)

        val image = Reference.to(requireNotNull(action.params?.get("image") as String))
        val itemView = requireNotNull(itemService.getItemView(entity))

        if (itemService.mutate(entity, ItemMutator.new().currentImage(image).build())) {
            callbackReceiver.onItemImageChanged(itemView)
        }
    }
}