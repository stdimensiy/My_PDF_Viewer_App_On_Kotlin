package com.example.mypdfvieweronkotlin.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.model.Repository
import com.example.mypdfvieweronkotlin.model.interfaces.CallBack

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository()
    var currentDocumentList = MutableLiveData<List<Document>>()

    fun fetchData() {
        repository.getDocumentsList(object : CallBack<List<Document>> {
            override fun onResult(value: List<Document>) {
                currentDocumentList.postValue(value)
                checkAllElements(value)
            }
        })
    }

    fun checkAllElements(items: List<Document>) {
        items.forEach { checkStatus(it) }
    }


    fun downloadItem(item: Document) {
        // Загрузка документа с удаленного сервера во внутреннее хранилище
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        Log.d("Моя проверка", "VM - иннициализация процесса загрузки---")
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.fileName}")
        repository.getDocument(item, getApplication())
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
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.fileName}")
        repository.deleteFile(item, getApplication())
        item.currentLiveData.postValue(LoadStatus.ERROR)
        //если все в порядке
        item.currentLiveData.postValue(LoadStatus.IS_MISSING)
    }

    fun checkStatus(item: Document) {
        // Принудительная проверка статуса документа при ошибкеили невыясненном состоянии
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        Log.d("Моя проверка", "VM - иннициализация процесса проверки стстуса---")
        Log.d("Моя проверка", "VM - элемента с адреса: ${item.name}")
        repository.fileIsPresent(item, getApplication())
    }

}