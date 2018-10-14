package com.github.rskupnik.thestory.shared

import com.github.rskupnik.thestory.shared.json.JacksonJsonParser
import com.github.rskupnik.thestory.shared.json.JsonParser

object DefaultImplementationProvider {
    fun jsonParser(): JsonParser = JacksonJsonParser()
}