package com.shiftkey.codingchallenge.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShiftViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun testGetErrorMessage() {
        val shiftViewModel = ShiftViewModel(ApplicationProvider.getApplicationContext())
        shiftViewModel.errorMessage
    }

    @Test
    fun testGetLoading() {
        val shiftViewModel = ShiftViewModel(ApplicationProvider.getApplicationContext())
        shiftViewModel.loading
    }

    @Test
    fun testShowShifts() {
        val shiftViewModel = ShiftViewModel(ApplicationProvider.getApplicationContext())
        shiftViewModel.showShifts()
    }

    @Test
    fun testFetchShifts() {
        val shiftViewModel = ShiftViewModel(ApplicationProvider.getApplicationContext())
        shiftViewModel.fetchShifts()
    }
}