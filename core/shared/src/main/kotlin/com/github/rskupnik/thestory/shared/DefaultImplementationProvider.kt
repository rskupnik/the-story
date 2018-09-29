package com.github.rskupnik.thestory.shared

import com.github.rskupnik.thestory.shared.external.JacksonJsonParser
import com.github.rskupnik.thestory.shared.external.JsonParser

object DefaultImplementationProvider {

    private val jsonParser: JsonParser = JacksonJsonParser()

    fun jsonParser(): JsonParser = jsonParser
}