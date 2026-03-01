package models.request.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSchemaRequest {
    @JsonProperty("message")
    private String message;
}
