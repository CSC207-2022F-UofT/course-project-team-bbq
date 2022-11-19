package search_use_case;
/**
 * The search Output Boundary.
 * <p>
 * Interface adapters.
 * @author Winston Chieng
 */
public interface SearchOutputBoundary {

    /**
     * return a Response Model with the results if there are results
     * @param results SearchResponseModel containing the result set
     * @return SearchResponseModel with changes as needed.
     */
    SearchResponseModel prepareSuccessView(SearchResponseModel results);

    /**
     * display an error if the search was unsuccessful
     * @param error a string containing the reason for the error
     * @return SearchResponseModel with error to be thrown.
     */
    SearchResponseModel prepareFailView(String error);
}
