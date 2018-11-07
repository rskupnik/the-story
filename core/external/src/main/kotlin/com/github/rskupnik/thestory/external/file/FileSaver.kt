package com.github.rskupnik.thestory.external.file

import com.github.rskupnik.thestory.external.Port

interface FileSaver : Port {
    fun saveSnapshot(filename: String) // TODO: Add Snapshot? Or Map<String, Any?>
}