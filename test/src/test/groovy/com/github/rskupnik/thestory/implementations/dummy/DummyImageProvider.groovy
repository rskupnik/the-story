package com.github.rskupnik.thestory.implementations.dummy

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.asset.ImageProvider
import org.jetbrains.annotations.NotNull

class DummyImageProvider implements ImageProvider {

    @Override
    Image getImage(@NotNull String id) {
        return new DummyImage(id)
    }
}
