package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.implementations.classpath.ClasspathFileLoader
import com.github.rskupnik.thestory.implementations.dummy.DummyAssetLoader

class Setup {
    static API application(FileLoader fileLoader, AssetLoader assetLoader) {
        final API api = Application.INSTANCE.init()
        api.provideImplementation(FileLoader.class, fileLoader)
        api.provideImplementation(AssetLoader.class, assetLoader)
        return api
    }

    static API standardApplication() {
        return application(new ClasspathFileLoader(), new DummyAssetLoader())
    }
}
