package models.response.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRecordResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("collection_id")
    private String collectionId;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("app_user_id")
    private String appUserId;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("deleted_at")
    private Date deletedAt;
    @JsonProperty("data")
    private RecordResponse data;
}
