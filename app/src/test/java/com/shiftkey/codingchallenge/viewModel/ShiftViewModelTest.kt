package com.shiftkey.codingchallenge.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shiftkey.codingchallenge.getOrAwaitValue
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShiftViewModelTest : TestCase() {

    private lateinit var shiftViewModel: ShiftViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        shiftViewModel = ShiftViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testGetErrorMessage() {
        shiftViewModel.errorMessage
    }

    @Test
    fun testGetLoading() {
        shiftViewModel.loading
        assertThat(shiftViewModel.loading.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun testShowShifts() {
        val value = shiftViewModel.showShifts().getOrAwaitValue()
        assertThat(value.toString(), not(nullValue()))
    }

}