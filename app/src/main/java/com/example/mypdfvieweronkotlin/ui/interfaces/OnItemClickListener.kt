package com.example.mypdfvieweronkotlin.ui.interfaces

import android.view.View
import com.example.mypdfvieweronkotlin.domain.Document

interface OnItemClickListener {
    fun onItemClickToDownload(view: View, position: Int, item: Document)
//
//    fun onItemLongClick(view: View?, position: Int)
}