package search_use_case;

import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;

import java.util.ArrayList;

/**
 * A data structure with the information to
 * create the ResultScreen
 * <p>
 * Interface Adapters.
 * @author Winston Chieng
 */
public class SearchResponseModel {
    final ArrayList<FlashcardSetDsRequestModel> result_set;

    /**
     * Create a SearchResponseModel
     * @param result_set the result set containing FlashcardSets from the search
     */
    public SearchResponseModel(ArrayList<FlashcardSetDsRequestModel> result_set){
        this.result_set = result_set;
    }

    /**
     * return the result set
     * @return the ArrayList of the result set
     */
    public ArrayList<FlashcardSetDsRequestModel> getResult_set(){
        return result_set;
    }
}
