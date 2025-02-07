# Модуль 1: Сетевые протоколы. HTTP. Rest

### REST vs SOAP
Создайте REST и SOAP веб-приложение с помощью Spring. Ссылку на репозиторий с выполненным заданием по теме необходимо всегда прикреплять в форму для ответов после текста задания.

---

### REST:

- Реализуйте серверную часть для обслуживания пользователей и разработайте архитектуру REST.
- [Создайте следующие объекты: User (id, name, surname, mail, tasks), Task (id, name, description, creationDate, deadLine).](impl/src/main/java/com/reksoft/module1rest/persistance/model)
- [Приложение должно поддерживать CRUD-операции для указанных объектов.](impl/src/main/java/com/reksoft/module1rest/controller/rest)
- [Работа должна определяться методами HTTP.](api/user-task-api.yml)
- Вы должны использовать такие подходы к ресурсам, как: users/%userId/tasks/%taskId.
- Протестируйте свое приложение с помощью любого REST-клиента: java client, IDEA, soapUI, curl, Postman.
- Реализуйте в ответе отображение возможных связанных действий с ресурсом, ознакомьтесь с решением Spring HATEOAS или аналогом.
- Визуализируйте свой REST API с помощью [Swagger](http://localhost:8080/swagger-ui/index.html#/).

---

SOAP:

- Создайте соответствующие веб-сервисы для упомянутых CRUD-операций для ваших объектов.
- Создайте WSDL и опубликуйте. [WSDL tasks](http://localhost:8080/ws/tasks.wsdl)/[WSDL users](http://localhost:8080/ws/users.wsdl)
- Создайте клиент и проверьте свои веб-сервисы.
- Обеспечьте поддержку транзакций.

---

## `Инструкция по запуску приложения:`

### Необходимо создать базу данных с слудующими параметрами:

- CREATE DATABASE user_task;
- username and password = *postgres*
- -localhost:5432

### Docker контейнер для Kafka

- Проверить REST часть можно с помощью [swagger](http://localhost:8080/swagger-ui/index.html#/)
- Проверить SOAP часть можно с помощью [Postman коллекции](postman/SOAP.postman_collection.json).

---
