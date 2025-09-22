package tests.users;

import groovy.json.JsonOutput;
import headerService.HeaderService;
import models.users.ListUsersResponse;
import models.users.UserDto;
import org.testng.annotations.Test;
import service.users.UsersService;
import support.LogAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;

//TODO отработка коллекции List

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
    public void sortUserById() {
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

    @Test
    public void getIdSaveInList(){
        List<UserDto> listUsers = prepairUserList(1);

        assertNotNull(listUsers);

    List<Integer> ids = new ArrayList<>();
    for(UserDto user : listUsers){
        ids.add(user.getId());

    }
        System.out.println(ids);
        assertFalse("id", ids.isEmpty());
        System.out.println(ids.isEmpty());
        assertEquals("id", listUsers.size(), ids.size());
    }

    @Test
    public void getUserListIdAndEmail(){
      List<UserDto> listUsers = prepairUserList(1);
        assertTrue(listUsers.size() > 0);
        UserDto firstUser = listUsers.get(0);
        UserDto lastUser = listUsers.get(listUsers.size() - 1);
        System.out.println(firstUser);
        System.out.println(lastUser);
        System.out.println(listUsers);

       assertTrue(firstUser.getEmail().contains("@"));
       assertTrue(lastUser.getEmail().contains("@"));


    }

    private List<UserDto> prepairUserList(int page) {
        return usersService
                .getUsersResponse(page)
                .statusCode(200)
                .contentType(HeaderService.contentTypeJson)
                .extract()
                .as(ListUsersResponse.class)
                .getData();
    }

    @Test
    public void getUserRandomValue(){
        List<UserDto> listUsers = prepairUserList(2);
        assertFalse(listUsers.isEmpty());
        int randomIndex = ThreadLocalRandom.current().nextInt(listUsers.size());
        UserDto randomUser = listUsers.get(randomIndex);
        System.out.println(randomUser);
        assertTrue(randomUser.getEmail().endsWith("reqres.in"));
    }

    @Test
    public void getAllUsers(){
        List<UserDto> firstPageUsers = prepairUserList(1);
        List<UserDto> secondPageUsers = prepairUserList(2);
        List<UserDto> generalUserList = new ArrayList<>(firstPageUsers);
        generalUserList.addAll(secondPageUsers);
        System.out.println(firstPageUsers);
        System.out.println(secondPageUsers);
        System.out.println(generalUserList);
        assertTrue(firstPageUsers.size() <= generalUserList.size());
        assertTrue(secondPageUsers.size() <= generalUserList.size());
        assertTrue(firstPageUsers.size() + secondPageUsers.size() == generalUserList.size());
    }

    @Test
    public void removeUsers(){
        List<UserDto> users = prepairUserList(1);
        users.remove(1);
        System.out.println(users);
        assertFalse(users.contains(1));//нет 1
    }

    @Test
    public void setUsers() {
        List<UserDto> users = prepairUserList(2);
        UserDto newUser = new UserDto();
        newUser.setId(999);
        newUser.setEmail("new.email@example.com");
        newUser.setFirstName("New");
        newUser.setLastName("User");
        newUser.setAvatar("https://example.com/avatar.jpg");
        UserDto oldUser = users.set(1, newUser);
        System.out.println("старый пользователь: " + oldUser);
        System.out.println("новый пользователь: " + users.get(1));
        assertEquals(newUser, users.get(1));
        assertNotEquals(oldUser, users.get(1));
    }
}

