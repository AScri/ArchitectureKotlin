package com.ascri.test.ui.base

import androidx.lifecycle.ViewModel
import com.ascri.test.utils.extensions.SingleRunner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val singleRunner = SingleRunner()

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}