# space-ships
Приложение для 
-создания 
-хранения
-удаления
-обновления данных
по космическим кораблям в БД.

1) Список всех кораблей GET http://localhost:8080/ship/get_all_ships
2) Добавить новый корабль POST http://localhost:8080/ship/add
body:
   {
   "name":"Mike2323",
   "planet":"Mars",
   "shipType":"TradeShip",
   "capacity":1334,
   "powerOfEngine":209.0,
   "maxSpeed": 233.23,
   "mileage":1234
   }
3) Удалить корабль по id DELETE http://localhost:8080/ship/delete/1
4) Найти корабль по id GET http://localhost:8080/ship/find/1
5) Обновить корабль POST http://localhost:8080/ship/update
body:
   {
   "name":"Mike2323",
   "planet":"Earth",
   "shipType":"TradeShip",
   "capacity":1334,
   "powerOfEngine":209.0,
   "maxSpeed": 233.23,
   "mileage":1234
   }