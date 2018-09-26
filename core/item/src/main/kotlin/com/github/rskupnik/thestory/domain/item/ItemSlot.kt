package com.github.rskupnik.thestory.domain.item

interface ItemSlot {
    fun fromRawObject(obj: Any?): ItemSlot?
}