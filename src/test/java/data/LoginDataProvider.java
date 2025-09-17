package data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginClass")
    public Object[][] loginData() {
        return new Object[][] {
                {"eve.holt@reqres.in", "cityslicka"},
                {"eve.holt@reqres.in", "pistol"}};
    }
}
