package com.shiftkey.codingchallenge.network

import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Response

class Repository {
    private object RepositoryInstanceHolder {
        internal val REPO_INSTANCE = Repository()
    }

    private val apiClient = ApiClient()

    suspend fun getShifts(lat: String, lng: String, radius: String, start: String, end: String) =
        apiClient.getAvailableShifts(
            lat = lat,
            lng = lng,
            radius = radius,
            start = start,
            end = end
        )

    companion object {
        val INSTANCE = RepositoryInstanceHolder.REPO_INSTANCE
    }
}

