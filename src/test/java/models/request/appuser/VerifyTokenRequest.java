package models.request.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifyTokenRequest {
    @JsonProperty("token")
    private String token;

    public VerifyTokenRequest(String token) { this.token = token; }
}
