package com.example.mypdfvieweronkotlin.domain

enum class LoadStatus {
    UNKNOWN,      //неизвестное состояние
    IS_MISSING,   //объект отсутствует в хранилище
    IS_LOADING,   //объект загружается
    IS_LOADED,    //объект загружен
    ERROR         //ошибка
}