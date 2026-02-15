package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordRequest {
    @JsonProperty("message")
    private String message;

    public RecordRequest(String message) {
        this.message = message;
    }
}
