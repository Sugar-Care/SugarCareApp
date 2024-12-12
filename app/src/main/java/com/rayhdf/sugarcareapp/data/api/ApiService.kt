package com.rayhdf.sugarcareapp.data.api

import com.rayhdf.sugarcareapp.data.model.GetPredictionsResponse
import com.rayhdf.sugarcareapp.data.model.GetTracksResponse
import com.rayhdf.sugarcareapp.data.model.LoginResponse
import com.rayhdf.sugarcareapp.data.model.NewsResponse
import com.rayhdf.sugarcareapp.data.model.PredictRequest
import com.rayhdf.sugarcareapp.data.model.PredictResponse
import com.rayhdf.sugarcareapp.data.model.RegisterResponse
import com.rayhdf.sugarcareapp.data.model.TrackResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Login and Register

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse




    // Prediction

    @POST("predictions/{userId}")
    suspend fun predict(
        @Path("userId") userId: String,
        @Body request: PredictRequest
    ): PredictResponse

    @GET("predictions/{userId}")
    suspend fun getPredictions(@Path("userId") userId: String): GetPredictionsResponse



    // Tracking

    @FormUrlEncoded
    @POST("track/{userId}")
    suspend fun track(
        @Path("userId") userId: String,
        @Field("sugarIntake") sugarIntake: Float,
        @Field("bodyWeight") bodyWeight: Float,
    ): TrackResponse

    @GET("track/{userId}")
    suspend fun getTracks(@Path("userId") userId: String): GetTracksResponse

    @DELETE("track/{userId}/{trackId}")
    suspend fun deleteTrack(
        @Path("userId") userId: String,
        @Path("trackId") trackId: String
    )

    // News
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
    ): NewsResponse


}

