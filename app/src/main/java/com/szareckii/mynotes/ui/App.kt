package com.szareckii.mynotes.ui

import android.app.Application
import com.szareckii.mynotes.di.appModule
import com.szareckii.mynotes.di.mainModule
import com.szareckii.mynotes.di.noteModule
import com.szareckii.mynotes.di.splashModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}