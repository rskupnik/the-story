package com.github.rskupnik.thestory.external.file

import com.github.rskupnik.thestory.external.Port

/**
 * This class is the main [Port] that the Application uses to interact with the underlying OS, storage hardware or
 * filesystem (saving).
 *
 * The user of the Application must provide an implementation for this interface.
 */
interface FileSaver : Port {

    /**
     * Save a snapshot of persistable [state] to a file ([filename]).
     */
    fun saveSnapshot(filename: String, state: Map<String, Any?>)
}