package com.ces.pulsera.data.remote.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:GetMacService by lazy {
        Retrofit.Builder()
            .baseUrl("https://servicios.cesmorelos.gob.mx/Pulseras/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetMacService::class.java)
    }
}