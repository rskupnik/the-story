package com.github.rskupnik.thestory.api.query.result

import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.item.domain.ItemView

data class EquipmentContent(val equipment: Map<EquipmentSlot, ItemView>)