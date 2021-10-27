package com.example.mypdfvieweronkotlin.ui.viewer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypdfvieweronkotlin.R

class ViewerFragment : Fragment() {

    companion object {
        fun newInstance() = ViewerFragment()
    }

    private lateinit var viewModel: ViewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}