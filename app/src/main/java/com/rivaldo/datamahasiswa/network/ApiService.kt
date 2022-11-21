package com.rivaldo.datamahasiswa.network

import com.rivaldo.datamahasiswa.response.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("{name}")
    fun getDataMahasiswa(@Path("name") name : String): Call<ApiResponse>
}