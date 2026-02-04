package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaResponse {
    @JsonProperty("powered_by")
    private String poweredBy;
    @JsonProperty("docs_url")
    private String docsUrl;
    @JsonProperty("upgrade_url")
    private String upgradeUrl;
    @JsonProperty("example_url")
    private String exampleUrl;
    @JsonProperty("variant")
    private String variant;
    @JsonProperty("message")
    private String message;
    @JsonProperty("cta")
    private CtaResponse cta;
    @JsonProperty("context")
    private String context;
}
