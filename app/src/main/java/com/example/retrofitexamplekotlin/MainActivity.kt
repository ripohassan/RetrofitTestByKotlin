package com.example.retrofitexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
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

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var activityBinding : ActivityMainBinding
    lateinit var mService : ApiInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : MovieAdapter
    lateinit var dialog: AlertDialog
  //  var firstLineArray = MutableList<Movie>(init = )
     private var firstLineArray: MutableList<Movie>? = null


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
        firstLineArray?.addAll(mutableList)
        adapter.notifyDataSetChanged()
        adapter.setClickListener(this)
        activityBinding.rvMovieList.adapter = adapter
    }

    override fun onItemClick(view: View, position: Int) {

        val intent = Intent(this,MovieDetailsActivity::class.java)
        intent.putExtra("title", "teset")
        intent.putExtra("url", firstLineArray?.get(position)?.imageurl)
        intent.putExtra("bio", firstLineArray?.get(position)?.bio)
        startActivity(intent)
        Toast.makeText(this@MainActivity, "TEST: " + position, Toast.LENGTH_SHORT).show()

    }


}