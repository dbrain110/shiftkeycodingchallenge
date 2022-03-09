package com.shiftkey.codingchallenge.network

import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShiftService {
    @GET("available_shifts")
    suspend fun getListOfShiftsAvailable(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("radius") radius: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<List<AvailableShiftResponse>>
}