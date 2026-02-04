package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetUserByIdResponse {
    @JsonProperty("data")
    private UserResponse data;
    @JsonProperty("support")
    private SupportResponse support;
    @JsonProperty("_meta")
    private MetaResponse meta;
}
