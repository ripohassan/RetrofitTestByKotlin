package com.example.retrofitexamplekotlin.Network

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"

    val retrofitService: ApiInterface
        get() = RetrofitClient.getClient(BASE_URL).create(ApiInterface::class.java)
}