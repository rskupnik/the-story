package com.github.rskupnik.thestory.background.domain

import com.github.rskupnik.thestory.persistence.Persistable

interface Background : Persistable {
    fun clone(): Background
}