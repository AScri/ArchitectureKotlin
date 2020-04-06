package com.ascri.test.utils.di.modules

import androidx.lifecycle.ViewModel
import com.ascri.test.ui.main.MainFragment
import com.ascri.test.ui.main.MainViewModel
import com.ascri.test.utils.di.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}