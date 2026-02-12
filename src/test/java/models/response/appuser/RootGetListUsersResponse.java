package models.response.appuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootGetListUsersResponse {
    @JsonProperty("data")
    private List<ListUsersResponse> data;
}
