package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserProjectResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("status")
    private String status;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("last_login_at")
    private Date last_login_at;
}
