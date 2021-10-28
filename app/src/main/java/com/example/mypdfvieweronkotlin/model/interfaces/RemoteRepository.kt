package com.example.mypdfvieweronkotlin.model.interfaces

import com.example.mypdfvieweronkotlin.domain.Document
import java.io.File

interface RemoteRepository {
    fun getDocumentsList(callBack: CallBack<List<Document>>)
    fun getDocument(item: Document, callBack: CallBack<File>)
}