package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.shared.Referable
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider

internal class Module(
        val definition: ModuleDefinition,
        val imageProvider: ImageProvider?
) : Referable{
    override val reference: Reference = Reference.to(definition.id)
}