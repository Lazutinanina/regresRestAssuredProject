package service.registration;

import constants.Endpoint;
import io.restassured.response.Response;
import support.Specs;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RegistrationService {
    public static void registerService(String email, String password){ {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        Response registerUserResponse = given()
                .spec(Specs.baseReqSpec())
                .when()
                .body(userData)
                .post(Endpoint.register)
                .then()
                .extract().response();
        registerUserResponse.prettyPrint();
    }
}}
