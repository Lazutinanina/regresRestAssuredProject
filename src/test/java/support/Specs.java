package support;

import constants.Endpoint;
import headerService.HeaderService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specs {

    //метод описывающий базовые характеристики реквест запроса
    public static RequestSpecification baseReqSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(Endpoint.baseURL)
                .setBasePath(Endpoint.API)
                .setContentType(HeaderService.contentTypeJson)
                .addHeader(HeaderService.headerApiKey, HeaderService.headerApiKeyValue)
                .build();
    }
}
