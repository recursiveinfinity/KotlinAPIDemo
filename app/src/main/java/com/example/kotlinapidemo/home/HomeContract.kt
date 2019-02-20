package com.example.kotlinapidemo.home

import com.example.kotlinapidemo.PassTime

interface HomeContract {
    interface View {
        fun showData(result: List<PassTime>)
        fun showError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun getISSPasses(latitude: String, longitude: String)
    }
}