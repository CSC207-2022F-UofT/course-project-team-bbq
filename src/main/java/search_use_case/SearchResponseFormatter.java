package search_use_case;

import entities.FlashcardSet;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;

public class SearchResponseFormatter implements SearchPresenter{

    @Override
    public SearchResponseModel prepareSuccessView(SearchResponseModel results) {
        ArrayList<FlashcardSetDsRequestModel> result_set = results.getResult_set();
        // change if anything needs to be changed
        return results;
    }

    @Override
    public SearchResponseModel prepareFailView(String error) {
        throw new SearchFail(error);
    }
}

