package com.example.mypdfvieweronkotlin.ui.main

import android.util.Log
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
        holder.iconDeleteOff()
        holder.iconDownloadOn()
        if (position == 2){
            holder.iconDeleteOn()
            holder.iconDownloadOff()
        }
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        val item = items[holder.adapterPosition]
        Log.d("Моя проверка", "Выполнился метод-сигнал , что вьюха приаттачена к окну")
        holder.ivDownload.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на кнопку загрузки $item")
        }
        holder.ivDelete.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на кнопку Удаления $item")
        }
        holder.clItem.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажатия на всю строку $item")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}