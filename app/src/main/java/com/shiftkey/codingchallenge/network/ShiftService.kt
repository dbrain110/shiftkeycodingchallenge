package com.shiftkey.codingchallenge.network

import com.shiftkey.codingchallenge.model.ShiftResponse
import retrofit2.http.GET

interface ShiftService {

    @GET(Constants.AVAILABLE_SHIFTS)
    suspend fun getListOfShiftsAvailable(): List<ShiftResponse>
}