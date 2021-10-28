package com.example.mypdfvieweronkotlin.model.interfaces

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document

interface LocalRepository {
    fun deleteFile(item: Document, context: Context)
    fun saveFile(item: Document, callBack: CallBack<Document>)
    fun fileIsPresent(item: Document, callBack: CallBack<Document>)
}