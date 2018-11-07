package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.file.FileSaver

internal class FileSaverDelegate : ImplementationDelegate<FileSaver>(), FileSaver {
    override fun saveSnapshot(filename: String, state: Map<String, Any?>) = getImplementation().saveSnapshot(filename, state)
}