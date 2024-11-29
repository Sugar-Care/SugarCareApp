package com.rayhdf.sugarcareapp.data.repository

import com.rayhdf.sugarcareapp.data.api.RetrofitInstance
import com.rayhdf.sugarcareapp.data.model.RegisterResponse

class UserRepository {

    suspend fun registerUser(name: String, email: String, password: String): RegisterResponse {
        return RetrofitInstance.api.registerUser(name, email, password)
    }

}