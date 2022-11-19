package search_use_case;

import entityRequestModels.CommonUserDsRequestModel;

import java.io.IOException;
import java.util.ArrayList;
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
     * @param search_input  the user search input
     * @param tags  the tags the user selected
     * @param user  the current user
     * @return  a response model with the result set
     */
    public SearchResponseModel create(String search_input, ArrayList<String> tags, CommonUserDsRequestModel user) throws IOException {
        SearchRequestModel requestModel = new SearchRequestModel(search_input, tags, user);

        return userInput.create(requestModel);
    }
}
