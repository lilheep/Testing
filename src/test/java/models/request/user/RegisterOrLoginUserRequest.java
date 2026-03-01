package models.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterOrLoginUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
