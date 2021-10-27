package com.example.mypdfvieweronkotlin.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R

class MainViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.title)
}