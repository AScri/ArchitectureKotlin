package com.ascri.test.utils.di

import android.content.Context
import com.ascri.test.TestApp
import com.ascri.test.ui.main.MainViewModel
import com.ascri.test.utils.di.factory.ViewModelBuilder
import com.ascri.test.utils.di.modules.ActivityBindingModule
import com.ascri.test.utils.di.modules.AppModule
import com.ascri.test.utils.di.modules.DataModule
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelBuilder::class,
        AppModule::class,
        DataModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<TestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApp): Builder

        fun build(): AppComponent
    }

    fun getAppContext(): Context
    fun getJackson(): ObjectMapper
    fun inject(mainViewModel: MainViewModel)
}