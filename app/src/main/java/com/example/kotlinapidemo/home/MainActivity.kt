package com.example.kotlinapidemo.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.kotlinapidemo.PassTime
import com.example.kotlinapidemo.PassesAdapter
import com.example.kotlinapidemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeContract.View {

    val passesAdapter = PassesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homePresenter: HomeContract.Presenter = HomePresenter(this)

        rvData.layoutManager = LinearLayoutManager(this)
        rvData.adapter = passesAdapter

        btnGetLocations.setOnClickListener {
            homePresenter.getISSPasses(etLatitude.text.toString(),
                etLongitude.text.toString())
        }

    }

    override fun showData(result: List<PassTime>) {
        passesAdapter.setData(result)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
