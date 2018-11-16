package com.github.rskupnik.thestory.proxy

import com.github.rskupnik.thestory.background.BackgroundService
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class BackgroundServiceProxy implements BackgroundService {

    private final BackgroundService actual

    public BackgroundServiceProxy(Object actual) {
        this.actual = actual as BackgroundService
    }

    @Override
    void setNoBackground() {
        actual.setNoBackground()
    }

    @Override
    void setNormalMappedBackground(@NotNull String image, @NotNull String normalImage) {
        actual.setNormalMappedBackground(image, normalImage)
    }

    @Override
    String getPersistenceKey() {
        return actual.getPersistenceKey()
    }

    @Override
    Object produceState() {
        return produceState()
    }

    @Override
    void ingestState(@Nullable Object state) {
        actual.ingestState(state)
    }
}
