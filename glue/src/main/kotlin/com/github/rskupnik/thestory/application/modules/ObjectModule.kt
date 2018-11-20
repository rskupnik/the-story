package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.`object`.ObjectInjectorHandle
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ObjectModule {

    @Provides @Singleton
    fun service(
            fileLoader: FileLoader,
            jsonParser: JsonParser
    ): ObjectService = ObjectInjectorHandle.service(
            fileLoader, jsonParser
    )
}