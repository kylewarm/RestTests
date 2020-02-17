package RestAssured.resource.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ResourcePage {

    Resource[] data;
    int total_pages;
    int total;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    int per_page;
    int page;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public Resource[] getData() {
        return data;
    }

    public void setData(Resource[] data) {
        this.data = data;
    }
}