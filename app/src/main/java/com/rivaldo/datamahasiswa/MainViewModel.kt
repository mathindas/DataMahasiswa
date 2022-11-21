package com.rivaldo.datamahasiswa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.datamahasiswa.network.ApiConfig
import com.rivaldo.datamahasiswa.response.ApiResponse
import com.rivaldo.datamahasiswa.response.MahasiswaItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val dataMahasiswa = MutableLiveData<List<MahasiswaItem>>()

    fun observeDataMahasiswa(): LiveData<List<MahasiswaItem>> = dataMahasiswa

    fun getData(name: String) {
        val client = ApiConfig.api.getDataMahasiswa(name)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        dataMahasiswa.postValue(responseBody.mahasiswa)
                    }
                } else {
                    Log.e("History", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("History", "onFailure: ${t.message}")
            }
        })
    }
}