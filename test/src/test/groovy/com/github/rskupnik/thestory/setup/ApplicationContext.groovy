package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API

import com.github.rskupnik.thestory.application.internal.InternalsContainer
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.shared.Service

class ApplicationContext {

    private final API api
    private final CallbackReceiver callbackReceiver
    private final FileSaver fileSaver
    private final InternalsContainer internalsContainer

    private ApplicationContext(API api, CallbackReceiver callbackReceiver, FileSaver fileSaver,
                               InternalsContainer internalsContainer) {
        this.api = api
        this.callbackReceiver = callbackReceiver
        this.fileSaver = fileSaver
        this.internalsContainer = internalsContainer
    }

    public static ApplicationContext standardApplication(CallbackReceiver callbackReceiver) {
        final InternalsContainer internalsContainer = new InternalsContainer()

        def API api = Setup.standardApplication(internalsContainer)
        if (callbackReceiver != null)
            api.provideImplementation(CallbackReceiver.class, callbackReceiver)

        def fileSaver = new InMemoryFileSaver()
        api.provideImplementation(FileSaver.class, fileSaver)

        return new ApplicationContext(api, callbackReceiver, fileSaver, internalsContainer)
    }

    public <T extends Service> void enableSpy(Class<T> clazz, T obj) {
        internalsContainer.internals.substitute(clazz, obj)
    }

    public <T extends Service> T getInternalImpl(Class<T> clazz) {
        return internalsContainer.internals.retrieve(clazz)
    }

    API getApi() {
        return api
    }

    CallbackReceiver getCallbackReceiver() {
        return callbackReceiver
    }

    FileSaver getFileSaver() {
        return fileSaver
    }
}
