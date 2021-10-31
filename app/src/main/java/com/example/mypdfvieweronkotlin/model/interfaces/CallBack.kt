package com.example.mypdfvieweronkotlin.model.interfaces

/**
 * Стандартный интерфейс "ответа"
 */
interface CallBack<T> {
    fun onResult(value: T)
}