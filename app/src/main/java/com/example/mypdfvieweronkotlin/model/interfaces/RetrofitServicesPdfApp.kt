package com.example.mypdfvieweronkotlin.model.interfaces

import com.example.mypdfvieweronkotlin.domain.Document
import retrofit2.Call
import retrofit2.http.GET

/**
 * Интерфейс сервиса для клиента Retrofit2
 * описан единственный метод для получения списка документов с сервера.
 *  - От index.php избавиться не смог (хотть по умолчанию и без него на запрос будет получен один ответ)
 * но так оно и правильнее и понятнее.
 */
interface RetrofitServicesPdfApp {
    @GET("index.php")
    fun getDocumentList(): Call<List<Document>>
}