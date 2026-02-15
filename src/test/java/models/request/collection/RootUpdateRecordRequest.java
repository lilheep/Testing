package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootUpdateRecordRequest {
    @JsonProperty("data")
    private UpdateRecordRequest data;

    public RootUpdateRecordRequest(UpdateRecordRequest data) {
        this.data = data;
    }
}
