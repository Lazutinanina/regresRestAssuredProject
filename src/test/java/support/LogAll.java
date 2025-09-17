package support;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class LogAll {
    @BeforeAll
    public static void logAll(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
