# My PDF Viewer App On Kotlin
Простое приложение для загрузки, организации и просмотра PDF материала
(предварительная проработка компонента с ориентиром на задачи строительного характера)

Небольшое приложение для отображения PDF-документов на Android с поддержкой анимации, жестов, масштабирования и двойного нажатия.
Написан на Kotlin с прицелом на архитектуру MVVM. Работа с PDF основана на библиотеке Android PdfViewer стабильной версии 2.8.2
На момент написания кода, не нашел ничего подходящего на Kotlin в качестве примера, поэтому и родился данный проект
как платформа для разработки и тестирования приложений, работающих с PDF

- Текущий результат (презентация)
<p align="center">
  <img src="https://github.com/stdimensiy/My_PDF_Viewer_App_On_Kotlin/raw/develop/snapshots/My_PDF_Viewer_App_On_Kotlin_1.png" width="150" title="Домашний фрагмент">
  <img src="https://github.com/stdimensiy/My_PDF_Viewer_App_On_Kotlin/raw/develop/snapshots/My_PDF_Viewer_App_On_Kotlin_2.png" width="150" alt="Фрагмент отображения PDF файла  (просмотрщик)">
  <img src="https://github.com/stdimensiy/My_PDF_Viewer_App_On_Kotlin/raw/develop/snapshots/My_PDF_Viewer_App_On_Kotlin_3.png" width="150" alt="Сообщение об ошибке">

</p>    

Приложение реализовано как Single-Activity с двумя фрагментами
Первый, домашний, стартовый фрагмент предназначен для организации хранения и доступа к документам PDF.
Во время работы этого компонента, приложение запрашивает и получает из сети через REST некоторый список документов (Файлов PDF и часть дополнительных данных). На экран устройства этот список выводится при помощи RecyclerView в виде вертикального списка. Каждый элемент которого представляет собой наименование документа и пиктограмму допустимого действия с ним.
Суть такова. Пользователь, имея актуальные данные о наличии тех или иных доступных ему для просмотра документах в формате PDF принимает решение какой именно документ загрузить к себе на устройство, посмотреть или удалить из памяти телефона.
Данное приложение специально сконструировано таким образом, что в нем нет функций, влияющих на состояние удаленного сервера и базы данных документов.
Это приложение меняет состояние только собственной внутренней памяти устройства в части наличия или отсутствия документа. Грубо говоря, если пользователю документ офлайн на телефоне нужен, он его скачивает, стал не нужен удаляет.
Для того чтобы посмотреть на документ, он должен быть скачан, в противном случае система уведомит пользователя о необходимости скачать и уже потом смотреть документ.
Список интерактивный, работа команд организована асинхронно. Для реализации работы этого компонента приложения применён Retrofit, для навигации применен Navigation Component
Второй фрагмент, собственно просмотрщик PDF документа.
Интерфейс простой, ничего лишнего не внедрял. Навигация обратно к списку - по стандартной системной кнопке «назад». Навигация из списка в просмотрщик конкретного документа, просто нажатие на элемент списка, вне контура пиктограммы действия.
Пиктограмма - действия (справа от наименования документа) сигнализирует о доступном в текущий момент времени действии для данного документа. Т.е. если документ загружен, доступное действие «Удалить», если документ НЕ загружен - доступное действие – «Загрузить».
Время от времени могут появляться иные значки в поле пиктограмм действия. Такие как круговой прогресс-бар, сигнализирующий о процессе загрузки документа на устройство. Сигнал о наличии ошибки, сообщающий о том, что доступ к документу НЕ может быть осуществлен, как минимум по причине ошибки доступа к файлу.
Может появится сигнал о неизвестном состоянии документа, означающий, что системой еще не проведен анализ в каком состоянии находится документ (загружен или нет, доступен или нет).
Все эти сигналы интерактивны и при клике на них автоматически запросят актуальное состояние документа. Однако при нормальной работе приложения, их появление должно иметь весьма непродолжительный и логичный характер.
