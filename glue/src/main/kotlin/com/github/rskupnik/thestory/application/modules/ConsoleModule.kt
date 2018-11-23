package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.application.internal.ConsoleCommandInitializer
import com.github.rskupnik.thestory.core.console.ConsoleInjectorHandle
import com.github.rskupnik.thestory.core.console.ConsoleService
import com.github.rskupnik.thestory.core.console.handler.ConsoleCommandInjectorHandle
import com.github.rskupnik.thestory.core.console.handler.PrintCommandHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ConsoleModule {

    @Provides @Singleton
    fun service(): ConsoleService = ConsoleInjectorHandle.service()

    @Provides @Singleton
    fun consoleCommandInitializer(
            printCommandHandler: PrintCommandHandler
    ): ConsoleCommandInitializer = ConsoleCommandInitializer()

    @Provides @Singleton
    fun printCommandHandler(
            consoleService: ConsoleService
    ): PrintCommandHandler = ConsoleCommandInjectorHandle.printCommandHandler(consoleService)
}