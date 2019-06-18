package com.pemwa.jokersapp

import android.app.Application
import com.pemwa.jokersapp.dagger.component.AppComponent
import com.pemwa.jokersapp.dagger.component.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        val component: AppComponent by lazy { DaggerAppComponent.builder().build() }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}