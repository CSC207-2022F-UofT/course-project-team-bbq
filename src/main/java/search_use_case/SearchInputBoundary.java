package search_use_case;

import java.io.IOException;
/**
 * The search input boundary.
 * <p>
 * Application Business Rules
 * @author Winston Chieng
 */
public interface SearchInputBoundary {
    /**
     * Creates a response model containing the result FlashCardSets from the search
     *
     * @param requestModel a data-structure containing the user input, search tags, and current user
     * @return a data structure with the data required to set up the resultsScreen
     */
    SearchResponseModel create(SearchRequestModel requestModel);
}
