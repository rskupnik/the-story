package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.Referable
import com.github.rskupnik.thestory.shared.Reference

internal class Module(
        val definition: ModuleDefinition,
        val imageProvider: ImageProvider?
) : Referable{
    override val reference: Reference = Reference.to(definition.id)
}