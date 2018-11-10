package com.github.rskupnik.thestory.setup

import com.github.rskupnik.thestory.application.API
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.implementations.proxy.ProxyCallbackReceiver

class ApplicationContext {

    private API api
    private CallbackReceiver callbackReceiver

    private ApplicationContext(API api, CallbackReceiver callbackReceiver) {
        this.api = api
        this.callbackReceiver = callbackReceiver
    }

    public static ApplicationContext standardApplication() {
        def (API api, CallbackReceiver callbackReceiver) = Setup.standardApplication()
        return new ApplicationContext(api, callbackReceiver)
    }

    API getApi() {
        return api
    }

    CallbackReceiver getCallbackReceiver() {
        return callbackReceiver
    }

    void provideCallbackReceiverProxyTarget(CallbackReceiver callbackReceiver) {
        ((ProxyCallbackReceiver)this.callbackReceiver).actual = callbackReceiver
    }
}
