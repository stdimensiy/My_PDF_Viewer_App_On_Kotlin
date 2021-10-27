package com.example.mypdfvieweronkotlin.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypdfvieweronkotlin.databinding.MainFragmentBinding
import okhttp3.*
import java.io.IOException

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //if (savedInstanceState == null) viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = MainAdapter(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainRecyclerView = binding.homeList
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun downloadPdf(pdfUrl: String) {
        val request = Request.Builder().url(pdfUrl).build()
        val client = OkHttpClient.Builder().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("failed to download")
                //complection(true)
            }

            override fun onResponse(call: Call, response: Response) {
                println("successful download")
                val pdfData = response.body?.byteStream()
                if (pdfData != null) {
                    try {
                        requireContext().openFileOutput("myFile.pdf", Context.MODE_PRIVATE)
                            .use { output ->
                                output.write(pdfData.readBytes())
                            }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                //complection(true)
            }

        })
    }

}