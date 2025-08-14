package com.example.applicationrickmorty.presentation.di

import android.content.Context
import com.example.applicationrickmorty.data.di.DataModule
import com.example.applicationrickmorty.domain.di.DomainModule
import com.example.applicationrickmorty.presentation.fragment.DetailFragment
import com.example.applicationrickmorty.presentation.fragment.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class] )
@Singleton
interface ApplicationComponent {

    @Component.Factory
    interface SingletonComponentFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)

}