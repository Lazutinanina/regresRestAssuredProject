package service.login;

import constants.JsonKeys;
import constants.Endpoint;
import headerService.HeaderService;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.login.LoginUserRequests;
import models.login.LoginUserResponse;
import support.Specs;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LoginService {
    public Response loginUserFromMap(String email, String password){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(JsonKeys.EMAIL, email);
        userData.put(JsonKeys.PASSWORD, password);
        return given()
                .when()
                .header(HeaderService.headerApiKey, HeaderService.headerApiKeyValue)
                .contentType(HeaderService.contentTypeJson)
                .body(userData)
                .post(Endpoint.baseURL + Endpoint.login)
                .then().assertThat().statusCode(200)
                .extract().response();
    }

    public Response loginUserFromPojo(String email, String password){
        LoginUserRequests userData = new LoginUserRequests(email, password);
        return given()
                .when()
                .header(HeaderService.headerApiKey, HeaderService.headerApiKeyValue)
                .contentType(HeaderService.contentTypeJson)
                .body(userData)
                .post(Endpoint.baseURL + Endpoint.login)
                .then().statusCode(200)
                .extract().response();
    }

    public Response loginUserDeserialize(String email, String password){
        LoginUserRequests userData = new LoginUserRequests(email, password);

        return given()
                .spec(Specs.baseReqSpec())
                .when()
                .body(userData)
                .post(Endpoint.login)
                .then()
                .extract()
                .response();
    }

    public LoginUserResponse loginUserWithBaseSpec(String email, String password){
        LoginUserRequests userData = new LoginUserRequests(email, password);
        return given()
                .spec(Specs.baseReqSpec())
                .when()
                .body(userData)
                .post(Endpoint.login)
                .then()
                .statusCode(200)
                .extract()
                .as(LoginUserResponse.class); //данные из ответа преобразуются в UserResponse
    }

    public ValidatableResponse loginUserValidResponse(String email, String password){//данные из ответа валидируютс
        LoginUserRequests userData = new LoginUserRequests(email, password);

        return given()
                .spec(Specs.baseReqSpec())
                .body(userData)
                .when()
                .post(Endpoint.login)
                .then();
    }
}
