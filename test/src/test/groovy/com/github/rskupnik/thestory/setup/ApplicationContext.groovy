package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver

class ApplicationContext {

    private final API api
    private final CallbackReceiver callbackReceiver
    private final FileSaver fileSaver
    private final Map<Class<Object>, Object> internals

    private ApplicationContext(API api, CallbackReceiver callbackReceiver, FileSaver fileSaver,
                               Map<Class<Object>, Object> internals) {
        this.api = api
        this.callbackReceiver = callbackReceiver
        this.fileSaver = fileSaver
        this.internals = internals
    }

    public static ApplicationContext standardApplication(CallbackReceiver callbackReceiver) {
        final Map<Class<Object>, Object> internals = new HashMap<>()

        def API api = Setup.standardApplication(internals)
        if (callbackReceiver != null)
            api.provideImplementation(CallbackReceiver.class, callbackReceiver)

        def fileSaver = new InMemoryFileSaver()
        api.provideImplementation(FileSaver.class, fileSaver)

        return new ApplicationContext(api, callbackReceiver, fileSaver, internals)
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
