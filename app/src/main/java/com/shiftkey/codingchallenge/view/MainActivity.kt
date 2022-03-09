package com.shiftkey.codingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.adapter.ShiftAdapter
import com.shiftkey.codingchallenge.databinding.ActivityMainBinding
import com.shiftkey.codingchallenge.viewModel.ShiftViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var shiftViewModel: ShiftViewModel
//    private val shiftAdapter = ShiftAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        shiftViewModel = ViewModelProvider(this, ShiftViewModel())
    }
}