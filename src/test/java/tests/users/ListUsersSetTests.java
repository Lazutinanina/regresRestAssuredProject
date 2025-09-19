package tests.users;

import headerService.HeaderService;
import models.users.ListUsersResponse;
import models.users.UserDto;
import org.testng.annotations.Test;
import service.users.UsersService;
import support.LogAll;

import java.util.*;

import static org.testng.AssertJUnit.*;


public class ListUsersSetTests extends LogAll {
    private final UsersService usersService = new UsersService();

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
    public void uniqueUserList() {
        List<UserDto> allUsers = prepairUserList(2);
        List<String> emails = new ArrayList<>();
        for (UserDto user : allUsers) {
            emails.add(user.getEmail());
        }
        Set<String> uniqueEmails = new HashSet<>(emails);
        System.out.println(allUsers);
        System.out.println(emails);
        System.out.println(uniqueEmails);
//        assertEquals(emails, uniqueEmails);
        assertEquals(emails.size(), uniqueEmails.size());
    }

    @Test
    public void uniqueUserIdList() {
        List<UserDto> allUsers = prepairUserList(2);
        List<Integer> ids = new ArrayList<>();
        for (UserDto user : allUsers) {
            ids.add(user.getId());
        }
        Set<Integer> uniqueEmails = new HashSet<>(ids);
        System.out.println(allUsers);
        System.out.println(ids);
        System.out.println(uniqueEmails);
        assertEquals(ids.size(), uniqueEmails.size());
    }

    @Test
    public void uniqueEmailWithInsertionOrderList() {
        List<UserDto> allUsers = prepairUserList(2);
        List<String> emails = new ArrayList<>();
        for (UserDto user : allUsers) {
            emails.add(user.getEmail());
        }
        Set<String> uniqueEmails = new LinkedHashSet<>(emails);
        System.out.println(allUsers);
        System.out.println(emails);
        System.out.println(uniqueEmails);
        assertEquals(emails.size(), uniqueEmails.size());
    }

    @Test
    public void sortedTreeSet() {
        List<UserDto> allUsers = prepairUserList(2);
        List<String> emails = new ArrayList<>();
        for (UserDto user : allUsers) {
            emails.add(user.getEmail());
        }
        Set<String> uniqueEmail = new HashSet<>(emails);
        Set<String> sorted = new TreeSet<>();

        for (UserDto user : allUsers) {
            sorted.add(user.getEmail());
        }
        Set<String> uniqueEmails = new LinkedHashSet<>(sorted);
        System.out.println(allUsers);
        System.out.println(sorted);
        System.out.println(uniqueEmails);
        System.out.println(uniqueEmail);
        assertEquals(sorted.size(), uniqueEmails.size());
        assertFalse(sorted.isEmpty());
    }

    @Test
    public void containsAll() {
        List<UserDto> allUsers = prepairUserList(2);
        List<String> emails = new ArrayList<>();
        for (UserDto user : allUsers) {
            emails.add(user.getEmail());
        }
        Set<String> emailsSet = new HashSet<>(emails);
        Set<String> subEmailsSet = new HashSet<>(Arrays.asList("byron.fields@reqres.in"));
        assertTrue(emailsSet.containsAll(subEmailsSet));
    }

    @Test
    public void addAll() {
        Set<String> emailsSet = new HashSet<>(Arrays.asList("test.@test.ru"));
        Set<String> subEmailsSet = new HashSet<>(Arrays.asList("byron.fields@reqres.in"));
        emailsSet.addAll(subEmailsSet);
        subEmailsSet.addAll(emailsSet);
        System.out.println(emailsSet);
        System.out.println(subEmailsSet);
        assertTrue(emailsSet.containsAll(subEmailsSet));
    }

    @Test
    public void clearAll() {
        Set<String> emailsSet = new HashSet<>(Arrays.asList("test.@test.ru"));
        assertTrue(emailsSet.isEmpty());
        emailsSet.clear();
        System.out.println(emailsSet);
        assertTrue(emailsSet.isEmpty());
    }
}
