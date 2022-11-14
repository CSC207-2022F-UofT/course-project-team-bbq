package search_use_case;

import java.io.IOException;

public interface SearchInputBoundary {

    SearchResponseModel create(SearchRequestModel requestModel) throws IOException;
}
