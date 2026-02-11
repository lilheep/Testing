package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyTokenResponse {
    @JsonProperty("session_token")
    private String sessionToken;
    @JsonProperty("expires_at")
    private Date expiresAt;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("note")
    private String note;
}
