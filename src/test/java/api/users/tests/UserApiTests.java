package api.users.tests;

import api.users.models.response.UserResponse;
import api.users.service.UserService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;


//TODO используется https://fakestoreapi.com/docs#tag/Users/operation/getAllUsers
public class UserApiTests {
    private final UserService userService = new UserService();

    @Test
    public void getAllUsers() {
        Response response = userService.getAllUsersResponse();
        response.prettyPrint();
        List<UserResponse> users = response.jsonPath().getList(".", UserResponse.class);
        assertNotNull(users);
    }


}
