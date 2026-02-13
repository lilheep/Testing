package models.response.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCollectionsResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("schema")
    private SchemaCollectionResponse schema;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
}
