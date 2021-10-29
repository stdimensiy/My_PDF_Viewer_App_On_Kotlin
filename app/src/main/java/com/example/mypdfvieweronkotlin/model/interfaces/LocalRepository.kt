package com.example.mypdfvieweronkotlin.model.interfaces

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document

interface LocalRepository {
    fun deleteFile(item: Document, context: Context)
    fun fileIsPresent(item: Document, context: Context)
}