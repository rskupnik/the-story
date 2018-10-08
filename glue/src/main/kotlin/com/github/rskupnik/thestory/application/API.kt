package com.github.rskupnik.thestory.application

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI

interface API {
    fun getCommandAPI(): CommandAPI
    fun getQueryAPI(): QueryAPI
}