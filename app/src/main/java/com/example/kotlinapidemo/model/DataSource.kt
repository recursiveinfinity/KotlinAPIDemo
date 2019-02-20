package com.example.kotlinapidemo.model

import com.example.kotlinapidemo.ISSResponse

interface DataSource {
    fun getDataForLocation(latitude: Float, longitude: Float)
}

interface DataObserver {
    fun onSuccess(result: ISSResponse)
    fun onError(error: String)
}