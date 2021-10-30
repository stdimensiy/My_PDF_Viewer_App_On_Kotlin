package com.example.mypdfvieweronkotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypdfvieweronkotlin.databinding.MainFragmentBinding
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.ui.interfaces.OnItemClickListener

class MainFragment : Fragment() {

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = MainAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        if (savedInstanceState == null) viewModel.fetchData()
        val mainRecyclerView = binding.homeList
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.currentDocumentList.observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemCommandBtnClick(
                view: View,
                position: Int,
                item: Document,
                command: Command,
            ) {
                when (command) {
                    Command.DOWNLOAD -> viewModel.downloadItem(item)
                    Command.DELETE -> viewModel.deleteItem(item)
                    Command.CHECK_STATUS -> viewModel.checkStatus(item)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}