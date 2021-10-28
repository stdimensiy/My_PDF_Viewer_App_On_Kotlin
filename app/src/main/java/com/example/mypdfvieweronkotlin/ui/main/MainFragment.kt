package com.example.mypdfvieweronkotlin.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypdfvieweronkotlin.databinding.MainFragmentBinding
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.ui.interfaces.OnItemClickListener

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = MainAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if (savedInstanceState == null) viewModel.fetchData()
        val mainRecyclerView = binding.homeList
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.currentDocumentList.observe(viewLifecycleOwner, {
            adapter.items = it
        })
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClickToDownload(
                view: View,
                position: Int,
                item: Document,
                command: Command
            ) {
                when (command) {
                    Command.DOWNLOAD -> {
                        Log.d("Моя проверка", "Фрагмент - Получен сигнал о загрузке!!!")
                        viewModel.downloadItem(item)
                    }
                    Command.DELETE -> {
                        Log.d("Моя проверка", "Фрагмент - получен сигнал о УДАЛЕНИИ!!")
                        viewModel.deleteItem(item)
                    }
                    Command.CHECK_STATUS -> {
                        Log.d("Моя проверка", "Фрагмент - получен сигнал о УДАЛЕНИИ!!")
                        viewModel.checkStatus(item)
                    }
                    Command.WATCH -> {
                        Log.d("Моя проверка", "Фрагмент - получен сигнал о Просмотре!")
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}