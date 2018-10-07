package com.github.rskupnik.thestory.domain.npc

import com.github.rskupnik.thestory.shared.Reference

interface NpcService {

    fun loadBlueprints(moduleReference: Reference)

    fun instantiate(reference: Reference): Reference?

    fun getNpcView(reference: Reference): NpcView?
}