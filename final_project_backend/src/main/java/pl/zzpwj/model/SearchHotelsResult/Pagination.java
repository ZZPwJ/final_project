package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
    public int currentPage;
    public String pageGroup;
    public int nextPageStartIndex;
    public int nextPageNumber;
    public String nextPageGroup;

    public Pagination() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageGroup() {
        return pageGroup;
    }

    public void setPageGroup(String pageGroup) {
        this.pageGroup = pageGroup;
    }

    public int getNextPageStartIndex() {
        return nextPageStartIndex;
    }

    public void setNextPageStartIndex(int nextPageStartIndex) {
        this.nextPageStartIndex = nextPageStartIndex;
    }

    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

    public String getNextPageGroup() {
        return nextPageGroup;
    }

    public void setNextPageGroup(String nextPageGroup) {
        this.nextPageGroup = nextPageGroup;
    }
}
