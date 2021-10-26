package com.example.mypdfvieweronkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pdfview = findViewById<PDFView>(R.id.pdfview)
        pdfview.fromAsset("VDNH_test.pdf")
            //.pages(0,1,2,5,7) //можно выбрать конкретные страницы отображения
            //.enableSwipe( true) // включает свайп
            //.swipeHorizontal(true) // назначает горизонтальный свайп
            .load()
    }
}