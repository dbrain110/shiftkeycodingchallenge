package com.shiftkey.codingchallenge.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import com.shiftkey.codingchallenge.network.Repository
import kotlinx.coroutines.*

class ShiftViewModel constructor(private val repo: Repository) : ViewModel() {

    val shiftList = MediatorLiveData<List<AvailableShiftResponse>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    var job: Job? = null


    fun fetchShifts(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getShifts(lat= "33", lng="-97", radius= "20", start="2022-03-08", end="2022-03-15")
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    shiftList.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error : ${response.message()}")
                }
            }
        }
    }
//    fun getShifts(){
//        viewModelScope.launch {
//            _shifts.postValue(repo.getShifts(lat= "33", lng="-97", radius= "20", start="2022-03-08", end="2022-03-15"))
//        }
//    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

}