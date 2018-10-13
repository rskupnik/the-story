package com.github.rskupnik.thestory.domain.`object`.injection

import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectBlueprintRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectInstanceRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectServiceImplementation
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ObjectModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, jsonParser: JsonParser, blueprintRepository: Repository<Blueprint>,
                instanceRepository: Repository<Instance>): ObjectService = ObjectServiceImplementation(
            fileLoader, jsonParser, blueprintRepository as ObjectBlueprintRepository,
            instanceRepository as ObjectInstanceRepository
    )

    @Provides @Singleton
    fun blueprintRepository(): Repository<out Blueprint> = ObjectBlueprintRepository()

    @Provides @Singleton
    fun instanceRepository(): Repository<out Instance> = ObjectInstanceRepository()
}