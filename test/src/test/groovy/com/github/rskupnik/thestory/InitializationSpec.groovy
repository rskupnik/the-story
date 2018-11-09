package com.github.rskupnik.thestory

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.application.Application
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.file.FileHandle
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.implementations.classpath.ClasspathFileLoader
import com.github.rskupnik.thestory.implementations.dummy.DummyAssetLoader
import spock.lang.Specification

class InitializationSpec extends Specification {

    def "should initialize game without errors"() {
        given:
        final API api = Application.INSTANCE.init()
        /*final FileLoader fileLoader = mockFileLoader(api)
        final FileHandle moduleHandle = Mock()
        fileLoader.getFileHandle("modules/unpacked/demo/module.json") >> moduleHandle
        fileLoader.loadAsString(moduleHandle) >> this.getClass().getResource("/module-standalone-simple.json").text*/

        api.provideImplementation(FileLoader.class, new ClasspathFileLoader())
        api.provideImplementation(AssetLoader.class, new DummyAssetLoader())

        when:
        api.commandAPI.initializeGame("demo")

        then:
        true
    }

    private def mockFileLoader(API api) {
        final FileLoader fileLoader = Mock()
        api.provideImplementation(FileLoader.class, fileLoader)
        return fileLoader
    }
}
