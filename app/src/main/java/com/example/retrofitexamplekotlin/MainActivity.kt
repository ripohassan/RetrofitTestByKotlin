package com.example.retrofitexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitexamplekotlin.Network.ApiInterface
import com.example.retrofitexamplekotlin.Network.Common
import com.example.retrofitexamplekotlin.adapter.MovieAdapter
import com.example.retrofitexamplekotlin.databinding.ActivityMainBinding
import com.example.retrofitexamplekotlin.model.Movie
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var activityBinding : ActivityMainBinding
    lateinit var mService : ApiInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : MovieAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        initVariables()
        initView()
        initFunc()
        initListener()
    }



    private fun initVariables() {
        mService = Common.retrofitService

    }

    private fun initView() {
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityBinding.root
        setContentView(view)
    }


    private fun initFunc() {
        activityBinding.rvMovieList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        activityBinding.rvMovieList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        getAllMovieList()

    }
    private fun initListener() {

    }


    private fun getAllMovieList() {
        dialog.show()

        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                setData(response.body() as MutableList<Movie>)
                dialog.dismiss()
            }

        })
    }

    private fun setData(mutableList: MutableList<Movie>) {
        adapter = MovieAdapter(baseContext,mutableList)
        adapter.notifyDataSetChanged()
        activityBinding.rvMovieList.adapter = adapter
    }


}