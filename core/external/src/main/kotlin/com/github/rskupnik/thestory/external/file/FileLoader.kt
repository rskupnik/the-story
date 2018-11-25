package com.github.rskupnik.thestory.external.file

import com.github.rskupnik.thestory.external.Port
import com.github.rskupnik.thestory.external.asset.Image

/**
 * This class is the main [Port] that the Application uses to interact with the underlying OS, storage hardware or
 * filesystem (loading).
 *
 * The user of the Application must provide an implementation for this interface.
 */
interface FileLoader : Port {

    /**
     * Get a [FileHandle] object, which represents a handle to a file.
     *
     * @param path the path to the file
     * @return an optional [FileHandle]
     */
    fun getFileHandle(path: String): FileHandle?

    /**
     * Get a [FileHandle] object, which represents a handle to a root folder of a
     * **module**
     *
     * @param module the id of the module to get a [FileHandle] for
     * @return an optional [FileHandle]
     */
    fun getHandleToModule(module: String): FileHandle?

    /**
     * Read the file pointed at by the **fileHandle** as a `String`
     *
     * @param fileHandle a [FileHandle] pointing to the file to be loaded
     * @return contents of the file as a `String`
     */
    fun loadAsString(fileHandle: FileHandle): String

    /**
     * Read the file pointed at by the **fileHandle** as an [Image]
     *
     * @param fileHandle a [FileHandle] pointing to the file to be loaded
     * @return contents of the file as an [Image]
     */
    fun loadAsImage(fileHandle: FileHandle): Image

    /**
     * Load a game state snapshot as a [Map] containing a [String] key and [List]
     * value, which in turn is a list of [Map] of [String] and [Object] pairs.
     *
     * @param filename name of the file to load the state from
     * @return the game state in the form described above
     */
    fun loadSnapshot(filename: String): Map<String, Any>?
}