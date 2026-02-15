package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRecordRequest {
    @JsonProperty("data")
    private RecordRequest dataRecord;

    public UpdateRecordRequest(RecordRequest dataRecord) {
        this.dataRecord = dataRecord;
    }
}
