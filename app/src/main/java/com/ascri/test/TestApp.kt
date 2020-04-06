package com.ascri.test

import com.ascri.test.utils.di.AppComponent
import com.ascri.test.utils.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }

    companion object {
        lateinit var appComponent: AppComponent
        const val TAG = "TEST_APP"
    }
}