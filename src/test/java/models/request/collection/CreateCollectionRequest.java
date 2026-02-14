package models.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCollectionRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("schema")
    private CreateSchemaRequest schema;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("visibility")
    private String visibility;

    public CreateCollectionRequest(String name, String slug, CreateSchemaRequest schema, String projectId, String visibility) {
        this.name = name;
        this.slug = slug;
        this.schema = schema;
        this.projectId = projectId;
        this.visibility = visibility;
    }
}
