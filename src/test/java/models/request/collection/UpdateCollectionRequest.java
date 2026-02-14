package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCollectionRequest {
    @JsonProperty("visibility")
    private String visibility;

    public UpdateCollectionRequest(String visibility) {
        this.visibility = visibility;
    }
}
