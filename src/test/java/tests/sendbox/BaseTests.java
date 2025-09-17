package tests.sendbox;

import constants.Endpoint;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseTests {
    @Test
    public void getUserTests() {
        given()
                .when()
                .basePath(Endpoint.API)
                .get(Endpoint.baseURL + Endpoint.user)
                .then()//то что выполнится после запроса
                .statusCode(200);

        Response response = given()
                .when()
                .get(Endpoint.baseURL + Endpoint.user)
                .then()//то что выполнится после запроса
                .statusCode(200)
                .extract().response();

        response.print();
    }

    @Test
    public void getUserByIdTests(){
        String id = "2";
        Response getUserByIdResponse = given()
                .when()
                .basePath(Endpoint.API)
                .get(Endpoint.baseURL + Endpoint.user + "/" +  id)
                .then()
                .extract().response();

        getUserByIdResponse.prettyPrint();
        System.out.println(getUserByIdResponse.statusCode());
        System.out.println(getUserByIdResponse.jsonPath().getString("data.first_name"));
        System.out.println(getUserByIdResponse.jsonPath().getString("support.url"));
        System.out.println(getUserByIdResponse.getCookies());
        System.out.println(getUserByIdResponse.getHeaders());
    }

    @Test
    public void getGoodByIdTests(){
        String id = "2";
        Response getUserByIdResponse = given()
                .when()
                .get(Endpoint.baseURL + Endpoint.user + "/" + id)
                .then()
                .extract().response();
    }

@Test
    public void registerUserTests(){
    HashMap<String, String> userData = new HashMap<>();
//    userData.put("username", "Janet");
    userData.put("email", "eve.holt@reqres.in");
    userData.put("password", "pistol");
        Response registerUserResponse = given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(userData)
                .post(Endpoint.baseURL + Endpoint.register)
                .then()
                .extract().response();
        registerUserResponse.prettyPrint();
    System.out.println(registerUserResponse.statusCode());
    }

    @Test
    public void loginUser(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put("email", "eve.holt@reqres.in");
        userData.put("password", "pistol");
        Response registerUserResponse = given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(userData)
                .post(Endpoint.baseURL + Endpoint.login)
                .then()
                .extract().response();
        registerUserResponse.prettyPrint();

    }

    @Test
    public void logoutUser(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put("email", "eve.holt@reqres.in");
        userData.put("password", "pistol");
        Response registerUserResponse = given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(userData)
                .post(Endpoint.baseURL + Endpoint.login)
                .then()
                .extract().response();
        String token = registerUserResponse.jsonPath().getString("token");
        System.out.println(token);

        Response logoutUserResponse = given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .body("{}")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .post(Endpoint.baseURL + Endpoint.logout)
                .then()
                .extract().response();

        logoutUserResponse.prettyPrint();
        System.out.println(logoutUserResponse.statusCode());
    }

    @Test
    public void getGoodByIdUpdatedTests(){
        Response getUserByIdUpdatetdResponse = given()
                .baseUri(Endpoint.baseURL)
                .basePath(Endpoint.API)
                .when()
                .header("x-api-key", "reqres-free-v1")
                .pathParam("id", 2)
                .get(Endpoint.user)
                .then()
                .extract().response();
        getUserByIdUpdatetdResponse.prettyPrint();
    }
}
