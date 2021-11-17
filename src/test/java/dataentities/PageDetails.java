package dataentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"page", "perPage", "total", "totalPages", "data", "support"})
public class PageDetails {

    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<Data> data;
    private Support support;

    public PageDetails() {
        this.page = 2;
        this.perPage = 6;
        this.total = 12;
        this.totalPages = 2;

        Data individualData = new Data();
        List<Data> data = new ArrayList<>();
        data.add(individualData);

        this.data = data;

        Support support = new Support();
        this.support = support;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("per_page")
    public int getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

}
