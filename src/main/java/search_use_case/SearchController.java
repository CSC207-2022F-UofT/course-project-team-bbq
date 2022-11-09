package search_use_case;

public class SearchController {

    private final SearchInputBoundary searchInput;

    public SearchController(SearchInputBoundary searchInput){
        this.searchInput = searchInput;
    }

    SearchResponseModel create(SearchRequestModel requestModel){
        return searchInput.create(requestModel);
    }
}
