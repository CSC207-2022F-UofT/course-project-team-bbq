package search_use_case;

import entityRequestModels.CommonUserDsRequestModel;

import java.io.IOException;
import java.util.ArrayList;

public class SearchController {

    final SearchInputBoundary userInput;

    public SearchController(SearchInputBoundary userInput){
        this.userInput = userInput;
    }

    public SearchResponseModel create(String search_input, ArrayList<String> tags, CommonUserDsRequestModel user) throws IOException {
        SearchRequestModel requestModel = new SearchRequestModel(search_input, tags, user);

        return userInput.create(requestModel);
    }
}
