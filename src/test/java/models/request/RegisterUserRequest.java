package models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
