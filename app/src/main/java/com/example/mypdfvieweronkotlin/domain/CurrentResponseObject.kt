package com.example.mypdfvieweronkotlin.domain

import androidx.lifecycle.MutableLiveData

data class CurrentResponseObject(val item: Document){
    val currentLiveData = MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN)

    fun whoIsThere(): String{
        return this.toString()
    }

}
