package com.example.kotlinapidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val issService = retrofit.create(ISSService::class.java)

        val passesAdapter = PassesAdapter()

        rvData.layoutManager = LinearLayoutManager(this)
        rvData.adapter = passesAdapter

        btnGetLocations.setOnClickListener {
            issService.getISSPasses(etLatitude.text.toString().toFloat(),
                etLongitude.text.toString().toFloat()).enqueue(object : Callback<ISSResponse> {
                override fun onFailure(call: Call<ISSResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ISSResponse>, response: Response<ISSResponse>) {
                    passesAdapter.setData(response.body()?.response ?: emptyList())
                }

            })
        }

    }
}
