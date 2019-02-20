package com.example.kotlinapidemo.home

import com.example.kotlinapidemo.ISSResponse
import com.example.kotlinapidemo.model.DataObserver
import com.example.kotlinapidemo.model.DataSource
import com.example.kotlinapidemo.model.RemoteDataSource

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter, DataObserver {
    override fun onSuccess(result: ISSResponse) {
        view.showData(result.response)
    }

    override fun onError(error: String) {
        view.showError(error)
    }


    override fun getISSPasses(latitude: String, longitude: String) {

        val dataSource: DataSource = RemoteDataSource(this)
        if (latitude.isEmpty() || longitude.isEmpty()) {
            view.showError("Latitude and Longitude cannot be empty")
            return
        }
        dataSource.getDataForLocation(latitude.toFloat(), longitude.toFloat())

    }

}