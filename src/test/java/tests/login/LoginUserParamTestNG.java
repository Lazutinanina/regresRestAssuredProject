package tests.login;

import data.LoginDataProvider;
import models.login.LoginUserResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.login.LoginService;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


public class LoginUserParamTestNG {
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"eve.holt@reqres.in", "cityslicka"},
                {"eve.holt@reqres.in", "pistol"}};
    }
    @Test(dataProvider = "loginData")
    public void loginParamTests(String email, String password) {
        LoginService loginService = new LoginService();
        LoginUserResponse loginUserResponse = loginService.loginUserWithBaseSpec(email, password);
        assertNotNull("Token is null", loginUserResponse.getToken());
        assertTrue("Токен короче допустимой длины символов", loginUserResponse.getToken().length() > 10);
    }


        @Test(dataProvider = "loginClass", dataProviderClass = LoginDataProvider.class)
        public void loginParamClass(String email, String password) {
            LoginService loginService = new LoginService();
            LoginUserResponse loginUserResponse = loginService.loginUserWithBaseSpec(email, password);
            assertNotNull("Token is null", loginUserResponse.getToken());
            assertTrue("Токен короче допустимой длины символов", loginUserResponse.getToken().length() > 10);
        }
}
