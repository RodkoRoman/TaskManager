# TaskManager
![Tasks](https://im.wampi.ru/2022/09/25/SKRINSOT-25.09.22_14.06.55.png)
Программа предназначена для ведения списка задач.


####Проект разработан для практического применения навыков, полученных в ходе изучения:
- ООП
- Языка программирования Java
- Spring Boot, MVC, Data
- Lombok
- Hibernate ORM
- SQL


##Превью 
![GifPreview](https://s5.gifyu.com/images/2022-09-25_14-59-30.gif)
При разработке применялись все основные принципы ООП:
- Инкапсуляцией скрывалась реализация
- При помощи наследования создавались сущности на базе существующих
- Использование полиморфизма, позволило взаимодействовать приложению с БД тремя разными способами
  - С использованием JDBC
  - С использованием EntityManager`а
  - С использованием Hibernate ORM

##Запуск приложения

1) На первом этапе, создал образ локальной БД PostgreSQL в Docker`е и запустил контейнер.
![GifDocker](https://s4.gifyu.com/images/2022-09-25_15-42-17.gif)


2) Запустил приложение и проверил подключение к БД.
![GifStartProject](https://s4.gifyu.com/images/2022-09-25_15-48-02.gif)


3) Протестировал функционал:
  - Добавил несколько задач в Postman`e и через DBeaver проверил наличие задач в БД.
    ![PostmanPOST](https://ic.wampi.ru/2022/09/25/POST.png)
    ![BdPost](https://im.wampi.ru/2022/09/25/BdPost.png)


  - Получил список всех задач в Postman`e.
![PostmanGET](https://ie.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.25.167b4c3b1f737f5f1c.png)


  - Получил задачу по id.
    ![PostmanGETbyId](https://ie.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.28.58.png)


  - Обновил задачу по id и проверил обновление в БД.
    ![PostmanPUTbyId](https://ic.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.32.18.png)
    ![BdPut](https://im.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.34.02.png)


  - Выставил флаг is_deleted по id и проверил обновление в БД.
    ![PostmanDELbyId](https://ie.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.38.00.png)
    ![BdDel](https://ie.wampi.ru/2022/09/25/SKRINSOT-25.09.22_16.38.42.png)

**По результатам тестирования все запросы работают корректно.**