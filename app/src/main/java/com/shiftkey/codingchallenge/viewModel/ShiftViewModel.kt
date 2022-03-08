package com.shiftkey.codingchallenge.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shiftkey.codingchallenge.model.ShiftResponse
import com.shiftkey.codingchallenge.network.Repository
import kotlinx.coroutines.launch

class ShiftViewModel(application: Application): AndroidViewModel(application) {
    private val _shifts = MutableLiveData<List<ShiftResponse>>()
    val shifts: LiveData<List<ShiftResponse>> = _shifts
    private val repo = Repository.INSTANCE


    fun getShifts(){
        viewModelScope.launch {
            _shifts.value = repo.getShifts()
        }
    }
}