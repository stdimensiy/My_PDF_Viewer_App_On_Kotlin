package com.example.mypdfvieweronkotlin.model.interfaces

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document

interface RemoteRepository {
    fun getDocumentsList(callBack: CallBack<List<Document>>)
    fun getDocument(item: Document, context: Context)
}