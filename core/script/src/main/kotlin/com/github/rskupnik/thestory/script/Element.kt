package com.github.rskupnik.thestory.script

abstract class Element internal constructor(val position: Int) : Comparable<Element> {

    abstract val length: Int

    override fun compareTo(other: Element): Int = position - other.position
}