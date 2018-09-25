package com.github.rskupnik.thestory.domain.shared.external


interface JsonParser {
    fun <R> parse(clazz: Class<R>, content: String): R
    fun <R> parseList(clazz: Class<R>, content: String): List<R>
}