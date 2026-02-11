package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RootUserMeResponse {
    @JsonProperty("data")
    private UserMeResponse data;
}
