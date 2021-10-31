package com.example.mypdfvieweronkotlin.domain

import com.example.mypdfvieweronkotlin.domain.Command.*

/**
 * Класс перечистений Command - набор доступных команд для выбранного объекта (применим в clickListener)
 *
 * * **[DOWNLOAD]**        - Загрузить объект
 * * **[DELETE]**          - Удалить объект
 * * **[WATCH]**           - Посмотреть объект
 * * **[CHECK_STATUS]**    - Проверить статус объекта
 * * **[SAY_ERROR]**       - Сообщить пользователю об ошибке
 */
enum class Command {
    DOWNLOAD,
    DELETE,
    WATCH,
    CHECK_STATUS,
    SAY_ERROR
}