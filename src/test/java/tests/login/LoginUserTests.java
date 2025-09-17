package tests.login;

import headerService.HeaderService;
import io.restassured.response.Response;
import models.login.LoginUserResponse;
import org.hamcrest.Matchers;

import org.testng.annotations.Test;
import service.login.LoginService;
import support.LogAll;

import static org.hamcrest.Matchers.blankString;
import static org.testng.AssertJUnit.*;

public class LoginUserTests extends LogAll {
    static String email = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String userName = "Janet";


    @Test
    public void loginUser(){
        LoginService loginService = new LoginService();
        LoginUserResponse loginUserResponse = loginService.loginUserWithBaseSpec(email, password);
        assertNotNull("Token is null", loginUserResponse.getToken());
        assertTrue("Токен короче допустимой длины символов", loginUserResponse.getToken().length() > 100);
    }


    @Test
    public void loginUserUserResponseTests(){
        LoginService loginService = new LoginService();
        Response response = loginService.loginUserDeserialize(email, password);
        assertEquals("Status code is not 200",200, response.statusCode());
        response.jsonPath().getString("token");
        assertNotNull("Token is null", response.jsonPath().getString("token"));
        response.prettyPrint();
        assertTrue("Ожидается другой тип данных", response.getContentType().contains(HeaderService.contentTypeJson));
    }
 @Test
    public void loginUserBDDTests(){
     new LoginService()
             .loginUserValidResponse(email, password)
             .statusCode(200)
             .body("token", Matchers.not(blankString()))//строка с токеном не пустая
             .body("token.lenght", Matchers.greaterThan(100));
 }

 @Test
    public void loginServiceParams(){}
}
