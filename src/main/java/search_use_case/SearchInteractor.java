package search_use_case;

public class SearchInteractor implements SearchInputBoundary{

    final SearchPresenter presenter;

    // figure out what else needs to be here
    public SearchInteractor(SearchPresenter presenter){
        this.presenter = presenter;
    }

    public SearchResponseModel create(SearchRequestModel requestModel){

        // search algorithm

        // return presenter.prepareSuccessView()
    }
}
