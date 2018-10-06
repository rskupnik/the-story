package com.github.rskupnik.thestory.domain.callback

enum class CallbackId {
    ON_EQUIP, ON_REMOVE;

    fun findInList(list: List<Callback>): Callback? = list.find { it.id == this }
}