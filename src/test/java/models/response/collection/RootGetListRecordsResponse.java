package models.response.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootGetListRecordsResponse {
    @JsonProperty("data")
    private List<CreateRecordResponse> data;
    @JsonProperty("meta")
    private MetaResponse meta;
}
