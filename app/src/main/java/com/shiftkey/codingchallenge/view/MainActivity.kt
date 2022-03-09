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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}