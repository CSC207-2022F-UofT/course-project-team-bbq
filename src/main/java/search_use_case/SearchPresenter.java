package search_use_case;
/**
 * The search presenter.
 * <p>
 * Interface adapters.
 * @author Winston Chieng
 */
public class SearchPresenter implements SearchOutputBoundary {

    @Override
    public SearchResponseModel prepareSuccessView(SearchResponseModel results) {
        return results;
    }

    @Override
    public SearchResponseModel prepareFailView(String error) {
        throw new SearchFailScreen(error);
    }
}

