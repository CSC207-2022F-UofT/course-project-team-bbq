package search_use_case;

import entities.FlashcardSet;

import java.util.ArrayList;

public class SearchResponseModel {
    ArrayList<FlashcardSet> result_set;

    public SearchResponseModel(ArrayList<FlashcardSet> result_set){
        this.result_set = result_set;
    }

    public ArrayList<FlashcardSet> getResult_set(){
        return result_set;
    }

    public void setResult_set(ArrayList<FlashcardSet> result_set){
        this.result_set = result_set;
    }
}
