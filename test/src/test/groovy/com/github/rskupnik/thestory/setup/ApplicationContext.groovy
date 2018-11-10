package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver

class ApplicationContext {

    private API api
    private CallbackReceiver callbackReceiver

    private ApplicationContext(API api, CallbackReceiver callbackReceiver) {
        this.api = api
        this.callbackReceiver = callbackReceiver
    }

    public static ApplicationContext standardApplication(CallbackReceiver callbackReceiver) {
        def API api = Setup.standardApplication()
        if (callbackReceiver != null)
            api.provideImplementation(CallbackReceiver.class, callbackReceiver)
        return new ApplicationContext(api, callbackReceiver)
    }

    API getApi() {
        return api
    }

    CallbackReceiver getCallbackReceiver() {
        return callbackReceiver
    }
}
