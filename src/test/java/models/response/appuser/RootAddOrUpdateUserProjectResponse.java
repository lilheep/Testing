package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootAddOrUpdateUserProjectResponse {
    @JsonProperty("data")
    private AddOrUpdateUserProjectResponse data;
}
