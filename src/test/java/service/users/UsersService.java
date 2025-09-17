package service.users;

import constants.Endpoint;
import io.restassured.response.ValidatableResponse;
import support.Specs;

import static io.restassured.RestAssured.given;


public class UsersService {
    public ValidatableResponse getUsersResponse(int page){
        return given()
                .spec(Specs.baseReqSpec())
                .queryParam("page", page)
                .when()
                .get(Endpoint.users)
                .then();
    }
}
