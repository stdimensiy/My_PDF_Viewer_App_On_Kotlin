package com.example.mypdfvieweronkotlin.model.interfaces

import com.example.mypdfvieweronkotlin.domain.Document
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServicesPdfApp {
    @GET("index.php")
    fun getDocumentList(): Call<List<Document>>
}