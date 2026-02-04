package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SupportResponse {
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
}
