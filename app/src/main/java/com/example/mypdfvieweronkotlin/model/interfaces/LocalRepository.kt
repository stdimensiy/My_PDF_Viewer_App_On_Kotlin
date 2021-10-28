package com.example.mypdfvieweronkotlin.model.interfaces

import com.example.mypdfvieweronkotlin.domain.Document

interface LocalRepository {
    fun deleteFile(item: Document, callBack: CallBack<Document>)
    fun saveFile(item: Document, callBack: CallBack<Document>)
    fun fileIsPresent(item: Document, callBack: CallBack<Document>)
}