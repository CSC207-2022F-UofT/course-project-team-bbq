package search_use_case;

public interface SearchPresenter {
    SearchResponseModel prepareSuccessView(SearchResponseModel results);

    SearchResponseModel prepareFailView(String error);
}
