package com.example.my_vk

import android.app.Application
import com.example.my_vk.di.DataModule

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(context = this)).build()
    }
}