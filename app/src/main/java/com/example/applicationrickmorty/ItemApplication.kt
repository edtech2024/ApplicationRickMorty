package com.example.applicationrickmorty

import android.app.Application
import com.example.applicationrickmorty.presentation.di.ApplicationComponent
import com.example.applicationrickmorty.presentation.di.DaggerApplicationComponent

class ItemApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }

}