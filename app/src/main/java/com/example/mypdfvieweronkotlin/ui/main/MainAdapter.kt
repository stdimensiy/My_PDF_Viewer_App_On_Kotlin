package com.example.mypdfvieweronkotlin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R
import com.example.mypdfvieweronkotlin.domain.Document

class MainAdapter(_fragment: Fragment) : RecyclerView.Adapter<MainViewHolder>() {
    private val fragment: Fragment = _fragment
    private var items: ArrayList<Document> = arrayListOf(
        Document("Первый"),
        Document("Втрой"),
        Document("Третий"),
        Document("Четвертый"),
        Document("Пятый"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main, parent, false)
        return MainViewHolder(root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }
}