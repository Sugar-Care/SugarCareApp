package com.rayhdf.sugarcareapp.data.repository

import com.rayhdf.sugarcareapp.data.api.RetrofitInstance
import com.rayhdf.sugarcareapp.data.model.GetPredictionsResponse
import com.rayhdf.sugarcareapp.data.model.LoginResponse
import com.rayhdf.sugarcareapp.data.model.PredictRequest
import com.rayhdf.sugarcareapp.data.model.PredictResponse
import com.rayhdf.sugarcareapp.data.model.RegisterResponse
import com.rayhdf.sugarcareapp.data.model.TrackResponse

class UserRepository {

    suspend fun registerUser(name: String, email: String, password: String): RegisterResponse {
        return RetrofitInstance.userApi.registerUser(name, email, password)
    }

    suspend fun loginUser(email: String, password: String): LoginResponse {
        return RetrofitInstance.userApi.loginUser(email, password)
    }

    suspend fun predict(userId: String, request: PredictRequest): PredictResponse {
        return RetrofitInstance.predictApi.predict(userId, request)
    }

    suspend fun getPredictions(userId: String): GetPredictionsResponse {
        return RetrofitInstance.userApi.getPredictions(userId)
    }

    suspend fun track(userId: String, sugarIntake: Float, bodyWeight: Float): TrackResponse {
        return RetrofitInstance.userApi.track(userId, sugarIntake, bodyWeight)
    }
}