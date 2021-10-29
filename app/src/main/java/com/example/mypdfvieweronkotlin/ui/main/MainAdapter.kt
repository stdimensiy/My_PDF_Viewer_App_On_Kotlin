package com.example.mypdfvieweronkotlin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.ui.interfaces.OnItemClickListener

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var items: List<Document> = listOf()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main, parent, false)
        return MainViewHolder(root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.iconDeleteOff()
        holder.iconDownloadOn()
        if (position == 2) {
            holder.iconDeleteOn()
            holder.iconDownloadOff()
        }
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        val item = items[holder.adapterPosition]
        //подписка на текущую лайвдату
        val currentLiveData: LiveData<LoadStatus> = item.currentLiveData
        currentLiveData.observeForever {
            when (it) {
                LoadStatus.IS_MISSING -> {   //не загружен
                    holder.iconDownloadOn()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.ERROR -> {    // состояние ошибки
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOn()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.IS_LOADING -> {    // состояние процесса загрузки
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOn()
                }
                LoadStatus.IS_LOADED -> {    // состояние  когда объект загружен
                    holder.iconDownloadOff()
                    holder.iconDeleteOn()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                else -> {
                    holder.iconDownloadOff() // прочие состояния, когда стстус объекта неизвестен
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOn()
                    holder.progressBarOff()
                }
            }
        }

        holder.ivDownload.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DOWNLOAD
            )
        }
        holder.ivDelete.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DELETE
            )
        }
        holder.ivUnknown.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.ivError.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.progressBar.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.clickItem.setOnClickListener {
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.WATCH
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}