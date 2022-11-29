package search_use_case;

import data_access.entity_request_models.FlashcardSetDsRequestModel;

import java.util.ArrayList;

/**
 * The search presenter.
 * <p>
 * Interface adapters.
 * @author Winston Chieng
 */
public class SearchPresenter implements SearchOutputBoundary {

    @Override
    public SearchResponseModel prepareSuccessView(ArrayList<FlashcardSetDsRequestModel> result_set) {
        return new SearchResponseModel(result_set);
    }

    @Override
    public SearchResponseModel prepareFailView(String error) {
        throw new SearchFailScreen(error);
    }
}

