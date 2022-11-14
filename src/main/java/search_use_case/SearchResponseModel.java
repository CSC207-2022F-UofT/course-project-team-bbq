package search_use_case;

import entities.FlashcardSet;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;

public class SearchResponseModel {
    ArrayList<FlashcardSetDsRequestModel> result_set;

    public SearchResponseModel(ArrayList<FlashcardSetDsRequestModel> result_set){
        this.result_set = result_set;
    }

    public ArrayList<FlashcardSetDsRequestModel> getResult_set(){
        return result_set;
    }

    public void setResult_set(ArrayList<FlashcardSetDsRequestModel> result_set){
        this.result_set = result_set;
    }
}
