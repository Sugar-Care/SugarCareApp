package com.rayhdf.sugarcareapp.data.repository

import com.rayhdf.sugarcareapp.data.api.RetrofitInstance
import com.rayhdf.sugarcareapp.data.model.LoginResponse
import com.rayhdf.sugarcareapp.data.model.PredictRequest
import com.rayhdf.sugarcareapp.data.model.PredictResponse
import com.rayhdf.sugarcareapp.data.model.RegisterResponse

class UserRepository {

    suspend fun registerUser(name: String, email: String, password: String): RegisterResponse {
        return RetrofitInstance.api.registerUser(name, email, password)
    }

    suspend fun loginUser(email: String, password: String): LoginResponse {
        return RetrofitInstance.api.loginUser(email, password)
    }

    suspend fun predict(request: PredictRequest): PredictResponse {
        return RetrofitInstance.api.predict(request)
    }
}