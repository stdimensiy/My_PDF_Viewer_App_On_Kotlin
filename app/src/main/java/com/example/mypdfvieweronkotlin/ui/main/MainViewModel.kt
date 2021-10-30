package com.example.mypdfvieweronkotlin.ui.main

import android.app.Application
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


    // Функция инициализирует процесс загрузки файла PDF
    fun downloadItem(item: Document) {
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        repository.getDocument(item, getApplication())
    }

    // Функция инициализирует удаление файла PDF (ни список, ни внешнее хранилище не затрагиваются)
    fun deleteItem(item: Document) {
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        repository.deleteFile(item, getApplication())
    }

    // Функция инициализирует проверку статуса документа
    fun checkStatus(item: Document) {
        item.currentLiveData.postValue(LoadStatus.IS_LOADING)
        repository.fileIsPresent(item, getApplication())
    }

}