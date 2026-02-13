package models.request.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("status")
    private String status;

    public UpdateUserRequest(String email, String status) {
        this.email = email;
        this.status = status;
    }
}
