package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserByIdResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("status")
    private String status;
    @JsonProperty("last_login_at")
    private Date last_login_at;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("environment_id")
    private String environmentId;
}
