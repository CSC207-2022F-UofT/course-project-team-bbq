package search_use_case;

public interface SearchPresenter {
    SearchResultsModel prepareSuccessView(SearchResultsModel results);

    SearchResultsModel prepareFailView(String error);
}
