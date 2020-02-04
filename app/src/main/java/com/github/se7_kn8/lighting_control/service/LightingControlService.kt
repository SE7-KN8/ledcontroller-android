package com.github.se7_kn8.lighting_control.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LightingControlService {

    @GET("/control/")
    fun info(): Call<String>

    @GET("version")
    fun version(): Call<String>

    @GET("lighting")
    fun currentColor(): Call<String>

    @POST("lighting")
    fun setColor(@Query("color") color: String): Call<Unit>

    @POST("lighting")
    fun setColor(@Query("color") color: String, @Query("time") ms: Int): Call<Unit>


    @POST("lighting/reset")
    fun reset(@Query("time") ms: Int): Call<String>

    @POST("lighting/reset")
    fun reset(): Call<String>

    @GET("lighting/mode")
    fun getCurrentMode(): Call<String>

    @POST("lighting/mode")
    fun setMode(@Query("mode") mode: String): Call<Unit>

    @GET("lighting/mode/list")
    fun getModes(): Call<String>

    @POST("lighting/mode/start")
    fun startMode(): Call<Unit>

    @POST("lighting/mode/start")
    fun startMode(@Query("multiplier") multiplier: Float): Call<Unit>

    @POST("lighting/mode/stop")
    fun stopMode(): Call<Unit>

}