package com.github.rskupnik.thestory.implementations.classpath

import com.github.rskupnik.thestory.external.file.FileHandle

class ClasspathFileHandle implements FileHandle {

    private final URL url;

    public ClasspathFileHandle(URL url) {
        this.url = url;
    }

    URL getUrl() {
        return url
    }
}
