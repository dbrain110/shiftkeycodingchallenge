package com.shiftkey.codingchallenge.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiftkey.codingchallenge.network.Repository
import java.lang.IllegalArgumentException

class ShiftViewModelFactory constructor(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            ShiftViewModel(this.repo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}