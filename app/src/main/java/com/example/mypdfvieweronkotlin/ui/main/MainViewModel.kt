package com.example.mypdfvieweronkotlin.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus

class MainViewModel : ViewModel() {
    val progLD = MutableLiveData<Boolean>()

    fun fetchData() {
        // должен загружаться список файлов из базы
    }

    fun fetchCurrentData(item: Document){
        // получаем данные из репозитория
        item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        Log.d("Моя проверка", "VM - текущая лайвдата: ${item.currentLiveData.value}")
    }

}