package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.application.delegates.ServiceDelegate
import com.github.rskupnik.thestory.application.internal.InternalsContainer
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver
import com.github.rskupnik.thestory.shared.Service
import spock.mock.MockingApi

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

    public  void enableSpy(Class<Service> clazz, Service obj) {
        internalsContainer.internals.substitute(clazz, obj)
    }

    Service getInternalImpl(Class<? extends Service> clazz) {
        return (internalsContainer.internals.implementations[clazz] as ServiceDelegate).target
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
