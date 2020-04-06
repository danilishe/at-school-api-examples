package org.example.api.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MyStepdefs {
    public static final Map<String, Object> storage = new HashMap<>();
    public static final String RESPONSE = "response";

    @Допустим("используются стандартные параметры запроса")
    public void используютсяСтандартныеПараметрыЗапроса() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/") // задаём базовый адрес каждого ресурса
                .addHeader("api_key", System.getProperty("api.key")) // задаём заголовок с токеном для авторизации
                .setAccept(ContentType.JSON) // задаём заголовок accept
                .setContentType(ContentType.JSON) // задаём заголовок content-type
                .log(LogDetail.ALL) // дополнительная инструкция полного логгирования для RestAssured
                .build(); // после этой команды происходит формирование стандартной "шапки" запроса.

    }

    @Если("выполнить запрос GET на эндпоинт {string}")
    public void выполнитьЗапросGETНаЭндпоинт(String endpoint) {
        Response response = given()
                .when()
                .get(endpoint);
        storage.put(RESPONSE, response);
    }

    @Тогда("код ответа {int}")
    public void кодОтвета(int expected) {
        Response response = (Response) storage.get(RESPONSE);
        response.then().statusCode(expected);
    }
}
