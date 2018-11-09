package com.github.rskupnik.thestory.implementations.dummy

import com.github.rskupnik.thestory.external.asset.Image

class DummyImage implements Image {

    private final String id

    public DummyImage(String id) {
        this.id = id
    }

    String getId() {
        return id
    }
}
