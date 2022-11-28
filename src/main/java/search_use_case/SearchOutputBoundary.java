package search_use_case;

import data_access.entity_request_models.FlashcardSetDsRequestModel;

import java.util.ArrayList;

/**
 * The search Output Boundary.
 * <p>
 * Interface adapters.
 * @author Winston Chieng
 */
public interface SearchOutputBoundary {

    /**
     * return a Response Model with the results if there are results
     * @param result_set the result set of flashcard sets
     * @return SearchResponseModel with changes as needed.
     */
    SearchResponseModel prepareSuccessView(ArrayList<FlashcardSetDsRequestModel> result_set);

    /**
     * display an error if the search was unsuccessful
     * @param error a string containing the reason for the error
     * @return SearchResponseModel with error to be thrown.
     */
    SearchResponseModel prepareFailView(String error);
}
