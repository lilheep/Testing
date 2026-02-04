package models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CtaResponse {
    @JsonProperty("label")
    private String label;
    @JsonProperty("url")
    private String url;
}
