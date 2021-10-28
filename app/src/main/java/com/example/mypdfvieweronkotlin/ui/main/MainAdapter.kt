package com.example.mypdfvieweronkotlin.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mypdfvieweronkotlin.R
import com.example.mypdfvieweronkotlin.domain.Command
import com.example.mypdfvieweronkotlin.domain.Document
import com.example.mypdfvieweronkotlin.domain.LoadStatus
import com.example.mypdfvieweronkotlin.ui.interfaces.OnItemClickListener

class MainAdapter() : RecyclerView.Adapter<MainViewHolder>() {
    private var items: ArrayList<Document> = arrayListOf(
        Document(
            "Первый",
            "shttp://souos.ru/testpdf/static/test_pr_p_1.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN)
        ),
        Document(
            "Втрой",
            "shttp://souos.ru/testpdf/static/test_pr_p_2.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADED)
        ),
        Document(
            "Третий",
            "shttp://souos.ru/testpdf/static/test_pr_p_3.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_LOADING)
        ),
        Document(
            "Четвертый",
            "shttp://souos.ru/testpdf/static/test_pr_p_4.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.ERROR)
        ),
        Document(
            "Пятый",
            "shttp://souos.ru/testpdf/static/test_pr_p_5.pdf",
            MutableLiveData<LoadStatus>(LoadStatus.IS_MISSING)
        )
    )
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
            Log.d("Моя проверка", "Изменен статус текущей лайвдаты ${it.name}")
            when (it) {
                LoadStatus.IS_MISSING -> {   //не загружен
                    Log.d("Моя проверка", "Адаптер - СОСТОЯНИЕ: объект в памяти отсутствует")
                    Log.d("Моя проверка", "меняю картинку на ЗАГРУЗИТЬ")
                    holder.iconDownloadOn()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.ERROR -> {    // состояние ошибки
                    Log.d("Моя проверка", "Адаптер - СОСТОЯНИЕ: получена ошибка")
                    Log.d("Моя проверка", "меняю картинку на ОШИБКА")
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOn()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                LoadStatus.IS_LOADING -> {    // состояние процесса загрузки
                    Log.d("Моя проверка", "Адаптер - СОСТОЯНИЕ: в процессе загрузки")
                    Log.d("Моя проверка", "меняю картинку на ПРОГРЕССБАР")
                    holder.iconDownloadOff()
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOn()
                }
                LoadStatus.IS_LOADED -> {    // состояние  когда объект загружен
                    Log.d("Моя проверка", "Адаптер - СОСТОЯНИЕ: объект загружен")
                    Log.d("Моя проверка", "меняю картинку на УДАЛИТЬ")
                    holder.iconDownloadOff()
                    holder.iconDeleteOn()
                    holder.iconErrorOff()
                    holder.iconUnknownOff()
                    holder.progressBarOff()
                }
                else -> {
                    Log.d("Моя проверка", "Адаптер - СОСТОЯНИЕ: неизвестно")
                    Log.d("Моя проверка", "меняю картинку на НЕПОНЯЯЯТНО")
                    holder.iconDownloadOff() // прочие состояния, когда стстус объекта неизвестен
                    holder.iconDeleteOff()
                    holder.iconErrorOff()
                    holder.iconUnknownOn()
                    holder.progressBarOff()
                }
            }
        }

        Log.d("Моя проверка", "Выполнился метод-сигнал , что вьюха приаттачена к окну")
        holder.ivDownload.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на кнопку загрузки $item")
            Log.d(
                "Моя проверка",
                "инициирую процесс загрузки (пока синхронно) команда подготовлена"
            )
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DOWNLOAD
            )
            Log.d("Моя проверка", "инициирую процесс загрузки (пока синхронно) команда передана")
        }
        holder.ivDelete.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на кнопку Удаления $item")
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.DELETE
            )
        }
        holder.ivUnknown.setOnClickListener {
            Log.d(
                "Моя проверка",
                "Отловлено событие нажания на кнопку неизвестного состояния $item"
            )
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.ivError.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на кнопку ошибки $item")
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.progressBar.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажания на прогрессбар $item")
            onItemClickListener?.onItemClickToDownload(
                holder.itemView,
                holder.adapterPosition,
                item,
                Command.CHECK_STATUS
            )
        }
        holder.clickItem.setOnClickListener {
            Log.d("Моя проверка", "Отловлено событие нажатия на всю строку $item")
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