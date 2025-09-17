package tests.users;

import headerService.HeaderService;
import models.users.ListUsersResponse;
import models.users.UserDto;
import org.testng.annotations.Test;
import service.users.UsersService;
import support.LogAll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class ListUsersTests extends LogAll {

    private final UsersService usersService = new UsersService();

    @Test
    public void getListUsersTests() {
        usersService
                .getUsersResponse(2)
                .statusCode(200)
                .contentType(HeaderService.contentTypeJson)
                .body("page", equalTo(2))
                .body("$", hasKey("data"))
                .body("data", not(empty()))//проверка что список не пустой
                .body("data.id", everyItem(notNullValue()))//id не null
                .body("data.email", everyItem(endsWith("reqres.in")))
                .body("data.email", everyItem(containsString("@")));

    }

    @Test
    public void sorUserById() {
        ListUsersResponse listUsers =
                usersService
                        .getUsersResponse(2)
                        .statusCode(200)
                        .extract()
                        .as(ListUsersResponse.class);
        List<Integer> ids = new ArrayList<>();
        for(UserDto userDto : listUsers.getData()){
            ids.add(userDto.getId());
        }
        List<Integer> sortedIds = new ArrayList<>(ids);
        Collections.sort(sortedIds, Collections.reverseOrder());// в обратном порядке сортировка
        System.out.println(ids);
        System.out.println(sortedIds);
    }
}

