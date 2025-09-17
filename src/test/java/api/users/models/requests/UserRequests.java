package api.users.models.requests;
//TODO используется https://fakestoreapi.com/docs#tag/Users/operation/getAllUsers
//TODO данные для отправки запроса: переменные, конструктор, геттеры и сеттеры

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequests {
    private int id;
    @JsonProperty("username")//в Java userName, в JSON "username"
    private String userName;
    private String email;
    private String password;

    public UserRequests(int id, String userName, String email, String password){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserRequests() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
