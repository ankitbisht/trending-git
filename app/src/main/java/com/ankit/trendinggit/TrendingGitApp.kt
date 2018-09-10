package com.ankit.trendinggit

import android.app.Application

class TrendingGitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TrendingGitApp
    }
}