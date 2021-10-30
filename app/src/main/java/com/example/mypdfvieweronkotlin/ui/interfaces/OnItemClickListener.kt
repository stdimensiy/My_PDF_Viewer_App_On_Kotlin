package com.example.mypdfvieweronkotlin.ui.interfaces

import android.view.View
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document

interface OnItemClickListener {
    fun onItemCommandBtnClick(view: View, position: Int, item: Document, command: Command)
}