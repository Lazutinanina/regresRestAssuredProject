package models.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserRequests {
    @JsonProperty("email")
    private String email;
    private String password;


    public LoginUserRequests(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUserRequests() {}

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
