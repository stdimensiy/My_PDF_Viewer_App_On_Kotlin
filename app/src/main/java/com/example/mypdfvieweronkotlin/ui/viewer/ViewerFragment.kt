package com.example.mypdfvieweronkotlin.ui.viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypdfvieweronkotlin.R
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class ViewerFragment : Fragment() {

    private lateinit var viewModel: ViewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.viewer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewerViewModel::class.java]
        val pdfView = view.findViewById<PDFView>(R.id.pdfview)
        val fileName = arguments?.getString("fileName")
        val dir: File = requireContext().filesDir
        val file = File(dir, fileName)
        if (file.isFile) {
            pdfView.fromFile(file)
                //.pages(0,1,2,5,7) //можно выбрать конкретные страницы отображения
                //.enableSwipe( true) // включает свайп
                //.swipeHorizontal(true) // назначает горизонтальный свайп
                .load()
        } else {
            pdfView.fromAsset("errorSign.pdf").load()
        }
    }
}