package com.example.mypdfvieweronkotlin.model

object RemoteConstants {
    /** Server endpoint  базовый адрес размещения PDF файлов
     *  или сервиса по их генерации (выдачи)  */
    const val PDF_SERVER_URL = "https://souos.ru/testpdf/static/"

    /** Base path  базовый адрес REST API сервиса ответственного за
     *  генерацию (выдачу) списка файлов PDF доступных к передаче  */
    const val LIST_PDF_URL = "https://souos.ru/testpdf/static/"
}