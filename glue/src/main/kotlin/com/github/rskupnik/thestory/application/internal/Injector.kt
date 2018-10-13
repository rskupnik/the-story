package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.ApiModule
import com.github.rskupnik.thestory.domain.item.injection.ItemModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    ItemModule::class
])
@Singleton
internal interface Injector {
    fun root(): Root
}