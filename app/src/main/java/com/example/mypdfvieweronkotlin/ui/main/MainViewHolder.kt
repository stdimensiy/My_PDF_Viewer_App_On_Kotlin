package com.example.mypdfvieweronkotlin.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val ivUnknown: ImageView = itemView.findViewById(R.id.imageView_unknown)
    val ivDownload: ImageView = itemView.findViewById(R.id.imageView_download)
    val ivDelete: ImageView = itemView.findViewById(R.id.imageView_delete)
    val ivError: ImageView = itemView.findViewById(R.id.imageView_error)
    val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    val clickItem: ConstraintLayout = itemView.findViewById((R.id.cl_item))

    fun iconDeleteOff() {
        ivDelete.visibility = View.GONE
    }

    fun iconDeleteOn() {
        ivDelete.visibility = View.VISIBLE
    }

    fun iconDownloadOff() {
        ivDownload.visibility = View.GONE
    }

    fun iconDownloadOn() {
        ivDownload.visibility = View.VISIBLE
    }

    fun iconUnknownOff() {
        ivUnknown.visibility = View.GONE
    }

    fun iconUnknownOn() {
        ivUnknown.visibility = View.VISIBLE
    }

    fun iconErrorOff() {
        ivError.visibility = View.GONE
    }

    fun iconErrorOn() {
        ivError.visibility = View.VISIBLE
    }

    fun progressBarOff() {
        progressBar.visibility = View.GONE
    }

    fun progressBarOn() {
        progressBar.visibility = View.VISIBLE
    }
}