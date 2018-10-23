package com.github.rskupnik.thestory.script

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.script.domain.Script
import com.github.rskupnik.wordplay.output.WordplayOutput

interface ScriptService {

    fun loadLocation(locationId: LocationId): WordplayOutput?

    fun parse(input: WordplayOutput): Script
}