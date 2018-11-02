package com.github.rskupnik.thestory.external.feedback

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.external.Port
import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.script.domain.Script

interface CallbackReceiver : Port {

    fun onLocationLoaded(script: Script)

    fun onDisplayOptions(options: List<OptionLabel>)

    fun onItemEquipped(item: ItemView)

    fun onItemRemoved(item: ItemView)

    fun onNewItemFound(item: ItemView)

    fun onItemImageChanged(item: ItemView)

    fun onEquipmentRefreshed()

    fun onInventoryRefreshed()

    fun onBackgroundChanged(background: Background)
}