package com.github.rskupnik.thestory.background.domain

import com.github.rskupnik.thestory.persistence.Persistable

class NoBackground: Background, Persistable {

    override fun toPersistableState(): Map<String, Any?>? = null

    override fun clone(): Background = NoBackground()
}