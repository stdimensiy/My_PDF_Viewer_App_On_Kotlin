package com.example.mypdfvieweronkotlin.domain

import androidx.lifecycle.MutableLiveData

data class Document(
    val name: String = "Без названия",
    val fileName: String = "noname.pdf",
    val currentLiveData: MutableLiveData<LoadStatus> = MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN),
)
