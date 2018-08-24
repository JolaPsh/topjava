**get all Users**
$ curl http://localhost:8080/topjava/rest/admin/users
[{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","enabled":true,"registered":"2018-08-24T16:38:30.183+0000","roles":["ROLE_ADMIN","ROLE_USER"],"caloriesPerDay":2000,"meals":null},{"id":100000,"name":"User","email":"user@yandex.ru","password":"password","enabled":true,"registered":"2018-08-24T16:38:30.183+0000","roles":["ROLE_USER"],"caloriesPerDay":2000,"meals":null}]

**get user with id**
$ curl http://localhost:8080/topjava/rest/admin/users/100001
{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","enabled":true,"registered":"2018-08-24T16:38:30.183+0000","roles":["ROLE_USER","ROLE_ADMIN"],"caloriesPerDay":2000,"meals":null}

**get all meals**
$ curl http://localhost:8080/topjava/rest/profile/meals
[{"id":100007,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"exceed":true},{"id":100006,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"exceed":true},{"id":100005,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"exceed":true},{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"exceed":false},{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"exceed":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"exceed":false}]

**get meals with id**
$  curl -s http://localhost:8080/topjava/rest/profile/meals/100003
{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"user":null}