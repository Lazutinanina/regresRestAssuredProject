package api.users.service;
//TODO используется https://fakestoreapi.com/docs#tag/Users/operation/getAllUsers
//TODO какие данные передаем, что проверяем, что возвращаем

import api.spec.Spec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final String USERS = "/users";

    public Response getAllUsersResponse() {
        return given()
                .spec(Spec.baseReqSpec())
                .when()
                .get(USERS)
                .then().statusCode(200)
                .extract().response();
    }
}
