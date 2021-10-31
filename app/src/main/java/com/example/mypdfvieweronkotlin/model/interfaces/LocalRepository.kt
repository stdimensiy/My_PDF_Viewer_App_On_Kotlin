package com.example.mypdfvieweronkotlin.model.interfaces

import android.content.Context
import com.example.mypdfvieweronkotlin.domain.Document
/**
 * Интерфейс локального репозитория
 */
interface LocalRepository {
    /**
     * Метод удаляет файл PDF из локального внутреннего хранилища, принадлежащий объекту **[Document.fileName]**
     * При этом формального колбека у данного метода нет.
     * В зависимости от результата изменяется состояние поля [Document.currentLiveData]
     * над которым и производится манипуляция
     */
    fun deleteFile(item: Document, context: Context)

    /**
     * Метод проверяет доступность (наличие) файла PDF, принадлежащуго объекту **[Document.fileName]**,
     * в локальном внутреннем хранилище. При этом формального колбека у данного метода нет.
     * В зависимости от результата изменяется состояние поля [Document.currentLiveData]
     * над которым и производится манипуляция
     */
    fun fileIsPresent(item: Document, context: Context)
}