package api.users.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO используется https://fakestoreapi.com/docs#tag/Users/operation/getAllUsers
//TODO для получения данных из запроса

@JsonIgnoreProperties(ignoreUnknown = true)//игнорируются все поля кроме: id, name,email,password

public class UserResponse {
    private int id;
    @JsonProperty("username")//в Java userName, в JSON "username"
    private String userName;
    private String email;
    private String password;

    //получение id
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
