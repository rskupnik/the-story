package com.github.rskupnik.thestory.script

class Text internal constructor(
        position: Int,
        val text: String,
        val id: String?,
        val clickable: Boolean,
        val color: Color?
): Element(position) {
    override val length: Int = text.length

    data class Color(val r: Int, val g: Int, val b: Int, val a: Int)
}