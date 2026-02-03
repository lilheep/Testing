package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.User;
import java.util.List;

@Data
public class GetUsersResponse {
    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total")
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("data")
    private List<User> data;
}
