package com.shiftkey.codingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.adapter.ShiftAdapter
import com.shiftkey.codingchallenge.databinding.ActivityMainBinding
import com.shiftkey.codingchallenge.viewModel.ShiftViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.baseFragment) {
                binding.toolbar.title = "Available Shifts"
                binding.toolbar.navigationIcon = null
            } else {
                binding.toolbar.title = "Shift Details"
                binding.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}