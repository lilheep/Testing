package org.example.models.response;

import org.example.models.User;
import java.util.List;

public class GetUsersResponse {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<User> data;

    public int getPage() { return page; }

    public int getPerPage() { return perPage; }

    public int getTotal() { return total; }

    public int getTotalPages() { return totalPages; }

    public List<User> getData() { return data; }

    public void setPage(int page) { this.page = page; }

    public void setPerPage(int perPage) { this.perPage = perPage; }

    public void setTotal(int total) { this.total = total; }

    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public void setData(List<User> data) { this.data = data; }
}
