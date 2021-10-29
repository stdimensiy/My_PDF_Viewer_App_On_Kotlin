package com.example.mypdfvieweronkotlin.model

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.model.interfaces.CallBack
import com.example.mypdfvieweronkotlin.model.interfaces.LocalRepository
import com.example.mypdfvieweronkotlin.model.interfaces.RemoteRepository
import com.example.mypdfvieweronkotlin.model.interfaces.RetrofitServicesPdfApp
import com.example.mypdfvieweronkotlin.model.retrofit.Common
import okhttp3.*
import java.io.File
import java.io.IOException

class Repository : LocalRepository, RemoteRepository {
    private val basePdfUrlPath = RemoteConstants.PDF_SERVER_URL
    private val networkServicesPdfApp: RetrofitServicesPdfApp = Common.retrofitService

    override fun deleteFile(item: Document, context: Context) {
        val dir: File = context.filesDir
        val file = File(dir, item.fileName)
        if (file.delete()) {
            item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        } else {
            item.currentLiveData.postValue(LoadStatus.IS_LOADED)
        }
    }

    override fun fileIsPresent(item: Document, context: Context) {
        // проверка наличия файла во внутреннем хранилище
        val dir: File = context.filesDir
        val file = File(dir, item.fileName)
        if (file.isFile) {
            item.currentLiveData.postValue(LoadStatus.IS_LOADED)
        } else {
            item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        }
    }

    override fun getDocumentsList(callBack: CallBack<List<Document>>) {
        networkServicesPdfApp.getDocumentList().enqueue(
            object : retrofit2.Callback<List<Document>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Document>>,
                    response: retrofit2.Response<List<Document>>
                ) {
                    response.body()?.let { callBack.onResult(it) }
                }

                override fun onFailure(call: retrofit2.Call<List<Document>>, t: Throwable) {
                    // На будущее. Добавить действие при отказе в получении списка файлов
                }
            })
    }

    override fun getDocument(item: Document, context: Context) {
        val request = Request.Builder().url(basePdfUrlPath + item.fileName).build()
        val client = OkHttpClient.Builder().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                item.currentLiveData.postValue(LoadStatus.ERROR)
            }

            override fun onResponse(call: Call, response: Response) {
                val pdfData = response.body?.byteStream()
                if (pdfData != null) {
                    try {
                        context.openFileOutput(item.fileName, Context.MODE_PRIVATE)
                            .use { output ->
                                output.write(pdfData.readBytes())
                            }
                        item.currentLiveData.postValue(LoadStatus.IS_LOADED)
                    } catch (e: IOException) {
                        item.currentLiveData.postValue(LoadStatus.ERROR)
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}