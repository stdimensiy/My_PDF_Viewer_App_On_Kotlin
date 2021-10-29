package com.example.mypdfvieweronkotlin.domain

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("name")
    val name: String = "Без названия",
    @SerializedName("file_name")
    val fileName: String = "noname.pdf",
    val currentLiveData: MutableLiveData<LoadStatus> = MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN),
)
