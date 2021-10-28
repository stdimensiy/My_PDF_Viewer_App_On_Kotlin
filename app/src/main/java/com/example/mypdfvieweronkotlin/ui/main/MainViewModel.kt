package com.example.mypdfvieweronkotlin.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus

class MainViewModel : ViewModel() {

    fun fetchData() {
        // должен загружаться список файлов из базы
    }

    fun downloadItem(item: Document) {
        // Загрузка документа с удаленного сервера во внутреннее хранилище
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        Log.d("Моя проверка", "VM - иннициализация процесса загрузки---")
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.url}")
        Thread.sleep(3_000)  // wait for 3 second
        // модуль контроля получаемого результата
        //...
        //если чтото пошло не так
        item.currentLiveData.postValue(LoadStatus.ERROR)
        //если все в порядке
        item.currentLiveData.postValue(LoadStatus.IS_LOADED)
    }

    fun deleteItem(item: Document) {
        // Удаление документа из внутренней памяти  (ни список ни внешнее хранилище не затрагиваются)
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        Log.d("Моя проверка", "VM - иннициализация процесса удаления---")
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.url}")
        Thread.sleep(3_000)  // wait for 3 second
        // модуль контроля получаемого результата
        //...
        //если чтото пошло не так
        item.currentLiveData.postValue(LoadStatus.ERROR)
        //если все в порядке
        item.currentLiveData.postValue(LoadStatus.IS_MISSING)
    }

    fun checkStatus(item: Document) {
        // Принудительная проверка статуса документа при ошибкеили невыясненном состоянии
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        Log.d("Моя проверка", "VM - иннициализация процесса проверки стстуса---")
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.name}")

        Thread.sleep(3_000)  // wait for 3 second
        // модуль контроля получаемого результата
        //...
        //если чтото пошло не так
        item.currentLiveData.postValue(LoadStatus.ERROR)
        //если все в порядке
        item.currentLiveData.postValue(LoadStatus.IS_MISSING)
    }

}