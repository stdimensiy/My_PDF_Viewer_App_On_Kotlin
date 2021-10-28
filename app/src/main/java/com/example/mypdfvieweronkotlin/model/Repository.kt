package com.example.mypdfvieweronkotlin.model

import androidx.lifecycle.MutableLiveData
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.model.interfaces.CallBack
import com.example.mypdfvieweronkotlin.model.interfaces.LocalRepository
import com.example.mypdfvieweronkotlin.model.interfaces.RemoteRepository
import java.io.File

class Repository : LocalRepository, RemoteRepository {
    private val plugDocumentList: ArrayList<Document> = arrayListOf(
        Document(
            "Первый",
            "shttp://souos.ru/testpdf/static/test_pr_p_1.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN)
        ),
        Document(
            "Втрой",
            "shttp://souos.ru/testpdf/static/test_pr_p_2.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADED)
        ),
        Document(
            "Третий",
            "shttp://souos.ru/testpdf/static/test_pr_p_3.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADING)
        ),
        Document(
            "Четвертый",
            "shttp://souos.ru/testpdf/static/test_pr_p_4.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.ERROR)
        ),
        Document(
            "Пятый",
            "shttp://souos.ru/testpdf/static/test_pr_p_5.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_MISSING)
        )
    )

    override fun deleteFile(item: Document, callBack: CallBack<Document>) {
        //TODO("Not yet implemented")
    }

    override fun saveFile(item: Document, callBack: CallBack<Document>) {
        //TODO("Not yet implemented")
    }

    override fun fileIsPresent(item: Document, callBack: CallBack<Document>) {
        //TODO("Not yet implemented")
    }

    override fun getDocumentsList(callBack: CallBack<List<Document>>) {
        callBack.onResult(plugDocumentList)
    }

    override fun getDocument(item: Document, callBack: CallBack<File>) {
        //TODO("Not yet implemented")
    }

}