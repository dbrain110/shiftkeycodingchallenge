package com.shiftkey.codingchallenge.network

class Repository {
    private object RepositoryInstanceHolder{
        internal val REPO_INSTANCE = Repository()
    }
    private val apiClient = ApiClient()

    suspend fun getShifts() = apiClient.getAvailableShifts()

    companion object{
        val INSTANCE = RepositoryInstanceHolder.REPO_INSTANCE
    }
}