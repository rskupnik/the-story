package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.implementations.classpath.ClasspathFileLoader
import com.github.rskupnik.thestory.implementations.dummy.DummyAssetLoader
import com.github.rskupnik.thestory.implementations.proxy.ProxyCallbackReceiver

class Setup {
    static Tuple2<API, CallbackReceiver> application(FileLoader fileLoader, AssetLoader assetLoader) {
        final API api = Application.INSTANCE.init()
        api.provideImplementation(FileLoader.class, fileLoader)
        api.provideImplementation(AssetLoader.class, assetLoader)

        final CallbackReceiver callbackReceiver = new ProxyCallbackReceiver()
        api.provideImplementation(CallbackReceiver.class, callbackReceiver)

        return new Tuple2<API, CallbackReceiver>(api, callbackReceiver)
    }

    static Tuple2<API, CallbackReceiver> standardApplication() {
        return application(new ClasspathFileLoader(), new DummyAssetLoader())
    }
}
