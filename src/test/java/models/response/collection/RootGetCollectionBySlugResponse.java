package models.response.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootGetCollectionBySlugResponse {
    @JsonProperty("data")
    private GetCollectionsResponse data;
}
