package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootCreateRecordRequest {
    @JsonProperty("data")
    private CreateRecordRequest data;

    public RootCreateRecordRequest(CreateRecordRequest data) {
        this.data = data;
    }
}
