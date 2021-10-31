package com.example.mypdfvieweronkotlin.model.interfaces

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document

/**
 * Интерфейс удаленного репозитория
 */
interface RemoteRepository {
    /**
     * Метод загружает список документов содержащих PDF файлы.
     * Загрузка спика производится с тестового статичного REST ресурса
     * **[Free REST-TEST API](https://souos.ru/testpdf/static/index.php)**
     */
    fun getDocumentsList(callBack: CallBack<List<Document>>)

    /**
     * Метод загружает конкретный PDF файл, имя которого получено из поля [Document.fileName]
     * Загрузка файла производится с тестового файлового ресурса
     * в котором размещены только те файлы имена которых переданы в ответе REST сервиса
     * **[Free REST-TEST API](https://souos.ru/testpdf/static/)**
     */
    fun getDocument(item: Document, context: Context)
}