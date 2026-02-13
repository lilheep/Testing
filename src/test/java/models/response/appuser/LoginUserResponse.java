package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserResponse {
    @JsonProperty("sent")
    private boolean sent;
    @JsonProperty("token")
    private String token;
    @JsonProperty("magicLink")
    private String magicLink;
    @JsonProperty("expires_in_minutes")
    private int expiresInMinutes;
    @JsonProperty("note")
    private String note;
}
