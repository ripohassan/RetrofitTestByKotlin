package com.example.retrofitexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitexamplekotlin.databinding.ActivityMainBinding
import com.example.retrofitexamplekotlin.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var movieBinding : ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_movie_details)

        initVariables()
        initView()
        initFunc()
        initListener()

    }

    private fun initVariables() {

    }

    private fun initView() {
        movieBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = movieBinding.root
        setContentView(view)
    }

    private fun initFunc() {
        val title:String = intent.getStringExtra("title").toString()
        val bio:String = intent.getStringExtra("bio").toString()
        val imageUrl:String = intent.getStringExtra("url").toString()
        movieBinding.tvTitle.setText(title)
        movieBinding.tvBio.setText(bio)
        Picasso.get().load(imageUrl).into(movieBinding.imvMovie)


    }

    private fun initListener() {

    }
}