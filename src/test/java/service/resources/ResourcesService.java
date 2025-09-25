package service.resources;

import constants.Endpoint;
import io.restassured.response.ValidatableResponse;
import support.Specs;

import static io.restassured.RestAssured.given;

public class ResourcesService {
    public ValidatableResponse getResourcesResponse(int page){
     return given()
             .spec(Specs.baseReqSpec())
             .queryParam("page", page)
             .when()
             .get(Endpoint.resources)
             .then();
}}
