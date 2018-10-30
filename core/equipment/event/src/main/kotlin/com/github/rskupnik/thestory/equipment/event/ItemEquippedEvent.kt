package com.github.rskupnik.thestory.equipment.event

import com.github.rskupnik.thestory.event.Event
import com.github.rskupnik.thestory.shared.Reference

data class ItemEquippedEvent(val item: Reference) : Event