package com.example.mypdfvieweronkotlin.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypdfvieweronkotlin.domain.CurrentResponseObject
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus

class MainViewModel : ViewModel() {
    val progLD = MutableLiveData<Boolean>()

    fun fetchData() {
        // должен загружаться список файлов из базы
    }

    fun downloadItem(item: Document) {
        //инициируется процесс загрузки конкретного документа
        Log.d("Моя проверка", "VM - Получена команда на загрузку эдемента по адресу: ${item.url}")
        var j=0
        for(i in 1..100000000){
            //println(i)
            j=i*123123/123+i
        }
        progLD.postValue(true)

    }

    fun fetchCurrentData(item: Document){
        // получаем данные из репозитория
        item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        Log.d("Моя проверка", "VM - текущая лайвдата: ${item.currentLiveData.value}")
    }

}