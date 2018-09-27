package com.github.rskupnik.thestory.shared.repository

import com.github.rskupnik.thestory.shared.Referable
import com.github.rskupnik.thestory.shared.Reference

/**
 * An in-memory extension of the [Repository] interface.
 *
 * It uses a backing [Map]<[Reference], [T]> to store the elements.
 *
 * @param T a [Referable] type stored in this [Repository]
 */
interface InMemoryRepository<T : Referable> : Repository<T> {

    val storage: MutableMap<Reference, T>

    /**
     * Saves [element] in memory.
     *
     * @param element the element to be saved
     * @return a [Reference] to the saved [element]
     */
    override fun save(element: T): Reference {
        storage[element.reference] = element
        return element.reference
    }

    /**
     * Saves a list of [elements] in memory.
     *
     * @param elements a [List] of elements to be saved
     * @return a [List] of [Reference]s of the saved elements
     */
    override fun save(elements: List<T>): List<Reference> = elements.map { save(it) }

    /**
     * Retrieves an optional value from memory by [reference].
     *
     * @param reference [Reference] of the element to retrieve
     * @return the optional result
     */
    override fun find(reference: Reference): T? = storage[reference]

    /**
     * Retrieves an immutable [List] of elements currently stored
     */
    override fun fetchAll(): List<T> = storage.values.toList()
}