package api.spec;

import api.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Spec {
    public static RequestSpecification baseReqSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .setContentType(Config.contentTypeJson)
                .build();
    }
}
