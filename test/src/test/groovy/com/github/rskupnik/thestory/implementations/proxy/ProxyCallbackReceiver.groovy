package com.github.rskupnik.thestory.implementations.proxy

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.external.dto.OptionLabel
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.script.domain.Script
import org.jetbrains.annotations.NotNull

class ProxyCallbackReceiver implements CallbackReceiver {

    private CallbackReceiver actual

    public void setActual(CallbackReceiver actual) {
        this.actual = actual
    }

    @Override
    void onLocationLoaded(@NotNull Script script) {
        actual.onLocationLoaded(script)
    }

    @Override
    void onDisplayOptions(@NotNull List<OptionLabel> options) {
        actual.onDisplayOptions(options)
    }

    @Override
    void onItemEquipped(@NotNull ItemView item) {
        actual.onItemEquipped(item)
    }

    @Override
    void onItemRemoved(@NotNull ItemView item) {
        actual.onItemRemoved(item)
    }

    @Override
    void onNewItemFound(@NotNull ItemView item) {
        actual.onNewItemFound(item)
    }

    @Override
    void onItemImageChanged(@NotNull ItemView item) {
        actual.onItemImageChanged(item)
    }

    @Override
    void onEquipmentRefreshed() {
        actual.onEquipmentRefreshed()
    }

    @Override
    void onInventoryRefreshed() {
        actual.onInventoryRefreshed()
    }

    @Override
    void onBackgroundChanged(@NotNull Background background) {
        actual.onBackgroundChanged(background)
    }
}
