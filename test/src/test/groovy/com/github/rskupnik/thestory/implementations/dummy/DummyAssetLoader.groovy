package com.github.rskupnik.thestory.implementations.dummy

import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.asset.ImageProvider
import com.github.rskupnik.thestory.external.file.FileHandle
import org.jetbrains.annotations.NotNull

class DummyAssetLoader implements AssetLoader {

    @Override
    ImageProvider loadImageProvider(@NotNull FileHandle fileHandle) {
        return new DummyImageProvider()
    }
}
