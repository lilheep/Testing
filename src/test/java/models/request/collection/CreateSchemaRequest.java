package models.request.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSchemaRequest {
    @JsonProperty("message")
    private String message;

    public CreateSchemaRequest(String message) {
        this.message = message;
    }
}
