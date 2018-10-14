package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.shared.DefaultImplementationProvider
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JsonModule {

    @Provides @Singleton
    fun jsonParser(): JsonParser = DefaultImplementationProvider.jsonParser()
}