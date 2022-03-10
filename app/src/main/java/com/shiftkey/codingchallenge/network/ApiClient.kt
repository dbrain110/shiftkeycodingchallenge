package com.shiftkey.codingchallenge.network

import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import retrofit2.Response

class ApiClient {
    private val apiService: ShiftService =
        ApiEndPoint.retrofitInstance.create(ShiftService::class.java)

    suspend fun getAvailableShifts(lat: String, lng: String, radius: String, start: String, end: String):
            Response<AvailableShiftResponse> = apiService.getListOfShiftsAvailable(lat = lat, lng = lng, radius = radius, start = start, end = end)
}