package com.github.rskupnik.thestory.external.asset

/**
 * Represents a file that contains a group of images.
 */
interface ImageProvider {

    /**
     * Retrieve a particular [Image] under the specified [id].
     */
    fun getImage(id: String): Image?
}