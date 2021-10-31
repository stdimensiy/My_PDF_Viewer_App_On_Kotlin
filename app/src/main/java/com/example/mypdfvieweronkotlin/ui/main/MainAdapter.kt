package com.example.mypdfvieweronkotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.ui.interfaces.OnItemClickListener
/**
 * Класс - Адаптер для RecyclerView
 * в данном решении он ведет себя частично как презентер, вьюхой для него является Holder
 */
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
                LoadStatus.IS_MISSING -> {
                    holder.iconDownloadOn()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.ERROR -> {
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOn()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.IS_LOADING -> {
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOn()
                }
                LoadStatus.IS_LOADED -> {
                    holder.iconDownloadOff()
                    holder.iconDeleteOn()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                else -> {
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOn()
                    holder.progressBarOff()
                }
            }
        }

        holder.ivDownload.setOnClickListener {
            onItemClickListener?.onItemCommandBtnClick(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DOWNLOAD
            )
        }
        holder.ivDelete.setOnClickListener {
            onItemClickListener?.onItemCommandBtnClick(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DELETE
            )
        }
        holder.ivUnknown.setOnClickListener {
            onItemClickListener?.onItemCommandBtnClick(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.ivError.setOnClickListener {
            onItemClickListener?.onItemCommandBtnClick(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.progressBar.setOnClickListener {
            onItemClickListener?.onItemCommandBtnClick(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.clickItem.setOnClickListener {
            if (item.currentLiveData.value?.equals(LoadStatus.IS_LOADED) == true) {
                val bundle = Bundle()
                bundle.putString("fileName", item.fileName)
                holder.itemView.findNavController().navigate(R.id.viewerFragment, bundle)
            } else {
                onItemClickListener?.onItemCommandBtnClick(
                    holder.itemView,
                    holder.adapterPosition,
                    item,
                    Command.SAY_ERROR
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}