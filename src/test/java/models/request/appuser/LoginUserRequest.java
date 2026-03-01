package models.request.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class LoginUserRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("project_id")
    private String projectId;
}
