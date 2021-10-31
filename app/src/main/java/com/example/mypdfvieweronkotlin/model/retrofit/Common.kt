package com.example.mypdfvieweronkotlin.model.retrofit

import com.example.mypdfvieweronkotlin.model.RemoteConstants
import com.example.mypdfvieweronkotlin.model.interfaces.RetrofitServicesPdfApp
/**
 * Объект (синглтон) клинета Retrofit2
 */

object Common {
    private const val BASE_URL = RemoteConstants.LIST_PDF_URL
    val retrofitService: RetrofitServicesPdfApp
        get() {
            return RetrofitClient.getClient(BASE_URL).create(RetrofitServicesPdfApp::class.java)
        }
}