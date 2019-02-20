package com.example.kotlinapidemo.model

import com.example.kotlinapidemo.BASE_URL
import com.example.kotlinapidemo.ISSResponse
import com.example.kotlinapidemo.ISSService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource(private val listener: DataObserver) : DataSource {

    override fun getDataForLocation(latitude: Float, longitude: Float) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val issService = retrofit.create(ISSService::class.java)

        issService.getISSPasses(latitude, longitude)
            .enqueue(object : Callback<ISSResponse> {
            override fun onFailure(call: Call<ISSResponse>, throwable: Throwable) {
                listener.onError(throwable.message ?: "An unknown error occurred")
            }

            override fun onResponse(call: Call<ISSResponse>, response: Response<ISSResponse>) {
                response.body()?.let {
                    listener.onSuccess(it)
                    return
                }
                listener.onError("An error occurred")
            }

        })
    }

}