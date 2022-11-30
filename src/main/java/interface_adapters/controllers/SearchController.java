package interface_adapters.controllers;

import search_use_case.SearchInputBoundary;
import search_use_case.SearchRequestModel;
import search_use_case.SearchResponseModel;

/**
 * Search Controller
 * <p>
 * Interface Adapters.
 * @author Winston Chieng
 */
public class SearchController {

    final SearchInputBoundary userInput;
    /**
     * Creates a SearchController
     * @param userInput the StudySettingsInputBoundary
     */
    public SearchController(SearchInputBoundary userInput){
        this.userInput = userInput;
    }

    /**
     * Creates a Response model to return a result set of FlashcardSets given user input and tags.
     * @param requestModel the request model with search input, tags, and current user
     * @return  a response model with the result set
     */
    public SearchResponseModel create(SearchRequestModel requestModel) {
        return userInput.create(requestModel);
    }
}
