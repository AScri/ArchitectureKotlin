package com.ascri.test.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ascri.test.data.models.ImageResponse
import com.ascri.test.data.repositories.ImageRepository
import com.ascri.test.ui.base.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(private val imageRepository: ImageRepository) : BaseViewModel() {
    private val exceptionHandler: CoroutineExceptionHandler
    private val mainJob: CoroutineScope
    private var transactionJob: CoroutineScope
    private val liveData = MutableLiveData<ImageResponse>()
    private val isLoading = MutableLiveData(false)
    fun getLiveData(): LiveData<ImageResponse> = liveData
    fun isLoading(): LiveData<Boolean> = isLoading
    private var page = 0

    init {
        exceptionHandler = CoroutineExceptionHandler { con, thr ->
            Log.e(MainFragment.TAG, "COROUTINES_EXCEPTIONS: catсhed $thr in $con", thr)
        }
        mainJob = coroutineScope + exceptionHandler + Job()
        transactionJob = coroutineScope + exceptionHandler + Job()
    }

    fun fetchNextUsers() {
        isLoading.value = true
        transactionJob.launch {
//            val response = imageRepository.restAPI.getImagesAsync(page, 2)
//            if (response.isSuccessful) {
//                withContext(Dispatchers.Main) {
//                    liveData.value = response.body()
//                    page++
//                    isLoading.value = false
//                }
//            }
//            else{
//                Log.e(MainFragment.TAG, "fetchUsers: error catched - $response")
//                isLoading.value = false
//            }
            withContext(Dispatchers.Main) {
                liveData.value = ImageResponse()
                page++
                isLoading.value = false
            }
        }
    }

    fun refresh(){
        page = 0
        fetchNextUsers()
    }
}
