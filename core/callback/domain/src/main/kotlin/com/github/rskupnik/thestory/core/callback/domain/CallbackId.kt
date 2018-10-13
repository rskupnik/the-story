package com.github.rskupnik.thestory.core.callback.domain

enum class CallbackId {
    ON_EQUIP, ON_REMOVE;

    fun findInList(list: List<Callback>): Callback? = list.find { it.id == this }
}