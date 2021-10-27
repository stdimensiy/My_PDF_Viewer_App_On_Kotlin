package com.example.mypdfvieweronkotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mypdfvieweronkotlin.ui.main.MainFragment
import com.github.barteksc.pdfviewer.PDFView
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        //downloadPdf("https://www.xeroxscanners.com/downloads/Manuals/XMS/PDF_Converter_Pro_Quick_Reference_Guide.RU.pdf")
//
//        var mContext: WeakReference<Context> = WeakReference(this)
//        mContext.get()?.let {
//
//        }
//        val a = filesDir
//        val b = applicationContext.filesDir
//        Log.d("Моя проверка", "Результат $a")
//        Log.d("Моя проверка", "Результат $b")
//
//        var file = File(applicationContext.filesDir, "testfile2.txt").createNewFile()
//        Log.d("Моя проверка", "Надеюсь получилось$file")

//        val pdfview = findViewById<PDFView>(R.id.pdfview)
//        pdfview.fromAsset("VDNH_test.pdf")
//            //.pages(0,1,2,5,7) //можно выбрать конкретные страницы отображения
//            //.enableSwipe( true) // включает свайп
//            //.swipeHorizontal(true) // назначает горизонтальный свайп
//            .load()
//    }

//    fun downloadPdf(pdfUrl: String) {
//        val request = Request.Builder().url(pdfUrl).build()
//        val client = OkHttpClient.Builder().build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                println("failed to download")
//                //complection(true)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                println("successful download")
//                val pdfData = response.body?.byteStream()
//                if (pdfData != null) {
//                    try {
//                        applicationContext.openFileOutput("myFile.pdf", Context.MODE_PRIVATE)
//                            .use { output ->
//                                output.write(pdfData.readBytes())
//                            }
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//                //complection(true)
//            }
//
//        })
    }
}