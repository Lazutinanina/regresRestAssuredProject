package api.products.service;

import api.spec.Spec;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ProductService {
    private static final String PRODUCTS = "/products";

    public Response getAllProductsResponse(){
        return given()
                .spec(Spec.baseReqSpec())
                .when()
                .get(PRODUCTS)
                .then().statusCode(200)
                .extract().response();
    }

    public Response getProductId(int productId){
        return given()
                .spec(Spec.baseReqSpec())
                .pathParam("id", productId) //параметр id
                .when()
                .get(PRODUCTS + "/{id}")
                .then().statusCode(200)
                .extract().response();
    }

    public ValidatableResponse getProductIdValidatable(int productId){
        return given()
                .spec(Spec.baseReqSpec())
                .pathParam("id", productId)
                .when()
                .get(PRODUCTS + "/{id}")
                .then();
    }
}
