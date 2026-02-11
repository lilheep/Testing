package models.request.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("project_id")
    private String projectId;

    public LoginUserRequest(String email, String projectId) {
        this.email = email;
        this.projectId = projectId;
    }
}
