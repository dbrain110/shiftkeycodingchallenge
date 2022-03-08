package com.shiftkey.codingchallenge.network

import com.shiftkey.codingchallenge.model.ShiftResponse

class ApiClient {
    private val apiService: ShiftService =
        ApiEndPoint.retrofitInstance.create(ShiftService::class.java)

    suspend fun getAvailableShifts(): List<ShiftResponse> = apiService.getListOfShiftsAvailable()
}