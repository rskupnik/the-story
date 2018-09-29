package com.github.rskupnik.thestory.shared

import com.github.rskupnik.thestory.shared.json.JacksonJsonParser
import com.github.rskupnik.thestory.shared.json.JsonParser

object DefaultImplementationProvider {

    private val jsonParser: JsonParser = JacksonJsonParser()

    fun jsonParser(): JsonParser = jsonParser
}