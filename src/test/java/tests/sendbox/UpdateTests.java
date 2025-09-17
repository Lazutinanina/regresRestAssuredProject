package tests.sendbox;

import constants.Endpoint;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class UpdateTests {
    private int id;

@BeforeEach //запуск перед каждым тестом
    public void setUp(){
        Response getUserId = given()
                .baseUri(Endpoint.baseURL)
                .header("x-api-key", "reqres-free-v1")
                .basePath(Endpoint.API)
                .when()
                .get(Endpoint.users)
                .then()
                .extract().response();
        List<String> usersId = getUserId.jsonPath().get("data.id");
    Random random = new Random();
    id = random.nextInt(usersId.size());
    System.out.println(id);
    }

    @Test
    public void updateUserTests(){
    int statusCode;
    String message;
    Response updateUserResponse = given()
            //базовый URL
            .baseUri(Endpoint.baseURL)
            .header("x-api-key", "reqres-free-v1")
            .basePath(Endpoint.API)
            .when()
            .pathParam("id", id)
            .put(Endpoint.user)
            //Проверяем статус-код. Убеждаемся, что сервер вернул успешный ответ, статус-код которого равен 200 OK.
            .then().assertThat().statusCode(200)
            .extract().response();
        updateUserResponse.prettyPrint();
//        statusCode = updateUserResponse.statusCode();
        message = updateUserResponse.jsonPath().getString("updatedAt");
//        assertEquals("Status code is not 200", statusCode, 200);
        assertNotNull("Message is null", message);
}
//    @Test
//    public void checkGetUsers() {
//        given().
//                // 1. Указываем базовый URL API
//                        baseUri("https://reqres.in/api").
//                header("x-api-key", "reqres-free-v1").
//                when().
//                // 2. Отправляем GET-запрос к эндпоинту /users с параметрами
//                        queryParam("page", 1).
//                queryParam("per_page", 3).
//                get("/users").
//                then().assertThat().
//                // 3. Проверяем статус код ответа
//                        statusCode(200).
//                // 4. Проверяем структуру JSON-ответа
//                        body("page", Matchers.equalTo(1)).
//                body("per_page", Matchers.equalTo(3)).
//                // 5. Проверяем, что в ответе 3 пользователя
//                        body("data.size()", Matchers.equalTo(3)).
//                // 6. Проверяем данные первого пользователя
//                        body("data[0].first_name", Matchers.equalTo("George")).
//                body("data[0].last_name", Matchers.equalTo("Bluth"));
//    }
}
