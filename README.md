
# ToDoListApp

### Описание:

API для сервиса управления задачами.

Реализована ролевая модель с двумя ролями User и Admin

**Admin** - может смотреть задачи любого пользователя, а также изменять поля задачи

**User** - может просматривать только свои задачи, менять статус по своим задачам

**Swagger** доступен по ссылке http://localhost:8080/swagger-ui/index.html

Для авторизации необходимо получить JWT токен по http://localhost:8080/auth  и использовать его в дальнейшем с префиксом Bearer
### Что понятно как делать но сделано из за недостаточно времени

- Настройка приватного docker registry и ci/cd используя github Actions
- Больше тестов

### Запуск сервиса

#### Сборка образа
Сборка осуществляется с помощью **docker compose**

В образ входит база PostgreSQL и backend-сервис. Для выбора портов хостовой    
системы необходимо в файле .env изменить значения соответствующих параметров.    
Порты по умолчанию: 8080 - для backend-сервиса, 6580 - для PostgreSQL.

Для сборки docker образа сервиса выполнить команду:



docker compose build
#### Запуск контейнеров

Для запуска контейнера на Windows выполнить команду:


docker compose up

#### Остановка контейнеров

Для остановки контейнера выполнить команду:


docker compose stop