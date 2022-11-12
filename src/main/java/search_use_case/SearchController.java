package search_use_case;

import java.util.ArrayList;

public class SearchController {

    final SearchInputBoundary userInput;

    public SearchController(SearchInputBoundary userInput){
        this.userInput = userInput;
    }

    SearchResponseModel create(String search_input, ArrayList<String> tags){
        SearchRequestModel requestModel = new SearchRequestModel(search_input, tags);

        return userInput.create(requestModel);
    }
}
