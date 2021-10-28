package com.example.mypdfvieweronkotlin.domain

import androidx.lifecycle.MutableLiveData

data class Document(
    val name: String = "Без названия",
    val url: String = "shttp://souos.ru/testpdf/static/noname.pdf",
    val currentLiveData: MutableLiveData<LoadStatus> = MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN),
)
