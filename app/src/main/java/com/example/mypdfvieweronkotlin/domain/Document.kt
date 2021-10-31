package com.example.mypdfvieweronkotlin.domain

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
/**
 * Класс Document - это модель данных, получаемых от **[Free REST-TEST API](https://souos.ru/)**
 *
 * * **[name]**      - ПОльзовательское (расширенное) наименование документа (файла) может содержать
 * большое количество символов, подвергаться форматированию и принимать в свой стостав специальные символы.
 * * **[fileName]**  - сорока содержащая только наименование файла в системе, с учетом всех правил
 * наименования файлов без исклчений. Также через (.) в конце обязательно должно быть указана расширение .pdf
 * * **[currentLiveData]**  MutableLiveData содержащая одно из фиксированных значений, по умолчанию **[LoadStatus.UNKNOWN]
 *
 * @constructor     создает объект, содержащий информацию файле PDF как о документе и назначает ему
 * свой уникальный экземпляр MutableLiveData с предустановленным значчением **[LoadStatus.UNKNOWN]
 * т.е. назначает по умолчанию статус документу "Неизвестен".  На протяжении жизни данного объекта в
 * системе (пока Document в списке есть и программа не удалена из памяти утсройства) значение этой
 * MutableLiveData будет изменяться в зависимости от фактического состояния объекта.
 */
data class Document(
    @SerializedName("name")
    val name: String = "Без названия",
    @SerializedName("file_name")
    val fileName: String = "noname.pdf",
    val currentLiveData: MutableLiveData<LoadStatus> = MutableLiveData<LoadStatus>(LoadStatus.UNKNOWN),
)
