package com.example.mypdfvieweronkotlin.model

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.model.interfaces.CallBack
import com.example.mypdfvieweronkotlin.model.interfaces.LocalRepository
import com.example.mypdfvieweronkotlin.model.interfaces.RemoteRepository
import okhttp3.*
import java.io.File
import java.io.IOException

class Repository : LocalRepository, RemoteRepository {
    private val basePdfUrlPath = "https://souos.ru/testpdf/static/"
    private val plugDocumentList: ArrayList<Document> = arrayListOf(
        Document(
            "Первый",
            "test_pr_p_1.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN)
        ),
        Document(
            "Втрой",
            "test_pr_p_2.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADED)
        ),
        Document(
            "Третий",
            "test_pr_p_3.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADING)
        ),
        Document(
            "Четвертый",
            "test_pr_p_4.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.ERROR)
        ),
        Document(
            "Пятый",
            "test_pr_p_5.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_MISSING)
        )
    )

    override fun deleteFile(item: Document, context: Context) {
        val dir: File = context.filesDir
        val file = File(dir, item.fileName)
        if (file.delete()) {
            Log.d("Моя проверка", "Репозиторий - Файл удален")
            item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        } else {
            Log.d("Моя проверка", "Репозиторий - Файл НЕ УДАЛЕН")
            item.currentLiveData.postValue(LoadStatus.IS_LOADED)
        }
    }

    override fun fileIsPresent(item: Document, context: Context) {
        // проверка наличия файла во внутреннем хранилище
        val dir: File = context.filesDir
        val file = File(dir, item.fileName)
        if(file.isFile){
            Log.d("Моя проверка", "Репозиторий - Файл Найден")
            item.currentLiveData.postValue(LoadStatus.IS_LOADED)
        } else {
            Log.d("Моя проверка", "Репозиторий - Файл НЕ НАЙДЕН")
            item.currentLiveData.postValue(LoadStatus.IS_MISSING)
        }
    }

    override fun getDocumentsList(callBack: CallBack<List<Document>>) {
        Log.d("Моя проверка", "Репозиторий - передаю список файлов")
        callBack.onResult(plugDocumentList)
    }

    override fun getDocument(item: Document, context: Context) {
        Log.d("Моя проверка", "Репозиторий - стартовал процесс загрузки файла")
        val request = Request.Builder().url(basePdfUrlPath + item.fileName).build()
        val client = OkHttpClient.Builder().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Моя проверка", "Репозиторий - файл НЕ загружен")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("Моя проверка", "Репозиторий - Файл загружен")
                Log.d("Моя проверка", "Репозиторий - Заголовки ответа${response.headers}")
                Log.d("Моя проверка", "Репозиторий - Заголовки ответа${response.message}")
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