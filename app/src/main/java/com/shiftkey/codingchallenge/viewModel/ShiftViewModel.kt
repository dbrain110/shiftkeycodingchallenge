package com.shiftkey.codingchallenge.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.network.Repository
import kotlinx.coroutines.*
import java.time.LocalDate

class ShiftViewModel constructor(private val repo: Repository) : ViewModel() {

    private val _shiftList = MediatorLiveData<List<ShiftsItem?>?>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    @RequiresApi(Build.VERSION_CODES.O)
    var start: LocalDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    var endShift: LocalDate = LocalDate.now().plusDays(7L)

    fun showShifts(): LiveData<List<ShiftsItem?>?> = _shiftList

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateStartEnd(){
        start  = endShift
        endShift = endShift.plusDays(7L)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchMoreShift(){
        updateStartEnd()
        fetchShifts()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    var job: Job? = null


    fun fetchShifts(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getShifts(lat = "33", lng = "-97", radius = "6", start = start.toString(), end = endShift.toString())
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