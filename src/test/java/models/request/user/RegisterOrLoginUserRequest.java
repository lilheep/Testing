package models.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterOrLoginUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public RegisterOrLoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
