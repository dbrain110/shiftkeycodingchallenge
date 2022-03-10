package com.shiftkey.codingchallenge.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.network.Repository
import kotlinx.coroutines.*

class ShiftViewModel constructor(private val repo: Repository) : ViewModel() {

    private val _shiftList = MediatorLiveData<List<ShiftsItem?>?>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun showShifts(): LiveData<List<ShiftsItem?>?> = _shiftList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    var job: Job? = null


    fun fetchShifts(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getShifts(lat= "33", lng="-97", radius= "20", start="2022-03-09", end="2022-03-16")
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    _shiftList.postValue(response.body()?.data?.flatMap { it?.shifts!! })
                    loading.value = false
                }else{
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}