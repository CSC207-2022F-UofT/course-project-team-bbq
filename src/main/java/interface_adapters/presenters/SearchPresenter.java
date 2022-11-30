package interface_adapters.presenters;

import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import interface_adapters.presenters.exceptions.SearchScreenFailed;
import search_use_case.SearchOutputBoundary;
import search_use_case.SearchResponseModel;

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
        throw new SearchScreenFailed(error);
    }
}

