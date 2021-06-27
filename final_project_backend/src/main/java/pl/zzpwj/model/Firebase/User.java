package pl.zzpwj.model.Firebase;

import lombok.Data;
import pl.zzpwj.model.Response;
import pl.zzpwj.model.SearchParameters;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String email;
    private String password;
    private String displayName;

    private List<Response> searchHistory = new ArrayList<>();
    private SearchParameters searchParameters;

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public List<Response> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<Response> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public void addSearchResponse(Response response){
        searchHistory.add(response);
    }
}
