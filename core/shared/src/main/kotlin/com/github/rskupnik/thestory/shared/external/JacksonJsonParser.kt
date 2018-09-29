package com.github.rskupnik.thestory.shared.external

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

internal class JacksonJsonParser : JsonParser {

    private val objectMapper = ObjectMapper()
            .registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    override fun <R> parse(clazz: Class<R>, content: String): R =
            objectMapper.readValue(content, clazz)

    override fun <R> parseList(clazz: Class<R>, content: String): List<R> {
        val collectionType = objectMapper.typeFactory.constructCollectionType(List::class.java, clazz)
        return objectMapper.readValue(content, collectionType)
    }
}