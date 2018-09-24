package com.github.rskupnik.thestory.domain.shared.repository

import com.github.rskupnik.thestory.domain.shared.Referable
import com.github.rskupnik.thestory.domain.shared.Reference

/**
 * Represents classes used as repositories for holding [Referable]s of type [T].
 */
interface Repository<T : Referable> {

    fun save(element: T): Reference

    fun save(elements: List<T>): List<Reference>

    fun find(reference: Reference): T?

    fun fetchAll(): List<T>
}