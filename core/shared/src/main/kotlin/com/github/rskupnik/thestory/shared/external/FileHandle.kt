package com.github.rskupnik.thestory.shared.external

/**
 * An abstraction that represents a handle to a file.
 *
 * The application is unaware of underlying system or disk - the user must provide a
 * [FileLoader] implementation that is capable of providing handles to files.
 */
interface FileHandle