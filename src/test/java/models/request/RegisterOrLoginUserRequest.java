package models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
