package com.ascri.test.utils.di.modules

import android.app.Application
import android.content.Context
import com.ascri.test.TestApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: TestApp): Context {
        return application.applicationContext
    }

    @Provides
    fun providesApplication(application: TestApp): Application {
        return application
    }
}