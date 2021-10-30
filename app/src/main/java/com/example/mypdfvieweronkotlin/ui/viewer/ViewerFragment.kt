package com.example.mypdfvieweronkotlin.ui.viewer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypdfvieweronkotlin.R
import com.github.barteksc.pdfviewer.PDFView

class ViewerFragment : Fragment() {

    private lateinit var viewModel: ViewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewerViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdfView = view.findViewById<PDFView>(R.id.pdfview)
        val fileName = arguments?.getString("fileName")
        println("Вывожу картинку... $fileName")
        pdfView.fromAsset("VDNH_test.pdf")
            //.pages(0,1,2,5,7) //можно выбрать конкретные страницы отображения
            //.enableSwipe( true) // включает свайп
            //.swipeHorizontal(true) // назначает горизонтальный свайп
            .load()
    }
}