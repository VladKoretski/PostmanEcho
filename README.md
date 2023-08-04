*test failed CI*  
[![Build status](https://ci.appveyor.com/api/projects/status/m3fxh17p2apwq7em/branch/main?svg=true)](https://ci.appveyor.com/project/VladKoretski/postmanecho-vqcha/branch/main)
  
*test succeded CI*  
[![Build status](https://ci.appveyor.com/api/projects/status/m22kuu5wbfkg34bp/branch/main?svg=true)](https://ci.appveyor.com/project/VladKoretski/postmanecho-f5nf5/branch/main)
  
# Домашнее задание к занятию «1.2. Тестирование API, CI»

## Задача №3: Postman Echo

**Важно**: эту задачу нужно выполнять в отдельном репозитории.

В этой задаче мы сэмулируем ситуацию, в которой SUT уже запущен, а мы из теста просто обращаемся к нему.

Есть специальный сервис, предназначенный для тестирования HTTP-запросов. Называется он [Postman Echo](https://docs.postman-echo.com). Никогда не тестируйте автоматизированными средствами веб-сервисы, если у вас нет на это письменного разрешения или если веб-сервисы специально не предназначены для этого.

Мы можем отправлять туда запросы и получать ответы.

С GET-запросами мы немного потренировались, теперь нас будут интересовать POST-запросы, а именно отправка тела запроса:

```java
// Given - When - Then
// Предусловия
given()
  .baseUri("https://postman-echo.com")
  .body("some data") // отправляемые данные (заголовки и query можно выставлять аналогично)
// Выполняемые действия
.when()
  .post("/post")
// Проверки
.then()
  .statusCode(200)
  .body(/* --> ваша проверка здесь <-- */)
;
```

Что нужно сделать:
1. Создайте новый проект на базе Gradle.
2. Добавьте необходимые зависимости. Если вы не пишите схему, то только REST assured.
3. Напишите тест, взяв сам запрос из кода выше.
4. Изучите ответ и напишите JSONPath-выражение вместо строк `/* --> ваша проверка здесь <--*/`, которое проверит, что в нужном поле хранятся отправленные вами данные. Обратите внимание, теперь у вас не массив, а объект.

Можете воспользоваться сервисами [jsonpath-tester](https://extendsclass.com/jsonpath-tester.html) или [jsonpath](https://jsonpath.com/) для быстрой проверки выражений. Описание синтаксиса [тут](https://testerslittlehelper.wordpress.com/2019/01/20/jsonpath-in-rest-assured/) и [тут](https://github.com/rest-assured/rest-assured/wiki/Usage#json-using-jsonpath).

Удостоверьтесь, что если вы будете использовать неверное выражение, то тесты упадут, в том числе и в CI.

Обратите внимание: если вам приходит вот такой объект:
```json
{
    "data": "some value"
}
```

то обратиться к нему с помощью JSONPath можно вот так: `data`, например: `.body("data", equalTo("some value"))`. То есть обращение к полю верхнеуровневого объекта, который называется безымянным, идёт без точки. В примере на лекциях у нас был массив и мы сразу обращались `[0]` — то же самое.

Если соберётесь отправлять текст не на латинице, то вам нужно будет выставлять кодировку, например, UTF-8:
```java
given()
  .baseUri("https://postman-echo.com")
  .contentType("text/plain; charset=UTF-8")
  .body("some data")
.when()
  .post("/post")
.then()
  .statusCode(200)
  .body(/* --> ваша проверка здесь <-- */)
;
```

Пришлите на проверку ссылку на ваш репозиторий. Удостоверьтесь, что в истории сборки были как success, так и fail, иначе будет не видно, как вы проверяли, что сборка падает в CI.
