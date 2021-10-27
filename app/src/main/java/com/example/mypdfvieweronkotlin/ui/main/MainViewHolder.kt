package com.example.mypdfvieweronkotlin.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.mypdfvieweronkotlin.R

class MainViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.title)
    val ivDownload: ImageView = itemView.findViewById(R.id.imageView_download)
    val ivDelete: ImageView = itemView.findViewById(R.id.imageView_delete)
    val clItem: ConstraintLayout = itemView.findViewById((R.id.cl_item))

    fun iconDeleteOff(){
        ivDelete.visibility = View.GONE
    }

    fun iconDeleteOn(){
        ivDelete.visibility = View.VISIBLE
    }

    fun iconDownloadOff(){
        ivDownload.visibility = View.GONE
    }

    fun iconDownloadOn(){
        ivDownload.visibility = View.VISIBLE
    }
}