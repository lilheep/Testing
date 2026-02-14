package models.response.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaResponse {
    @JsonProperty("page")
    private int page;
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("total")
    private int total;
    @JsonProperty("pages")
    private int pages;
}
