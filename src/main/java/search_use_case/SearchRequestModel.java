package search_use_case;
import java.util.ArrayList;

public class SearchRequestModel {

    private String search_input;
    private ArrayList<String> tags;

    public SearchRequestModel(String search_input, ArrayList<String> tags){
        this.search_input = search_input;
        this.tags = tags;
    }

    String getSearch_input(){
        return search_input;
    }
    void setSearch_input(String search_input){
        this.search_input = search_input;
    }

    ArrayList<String> getTags(){
        return tags;
    }

    void setTags(ArrayList<String> tags){
        this.tags = tags;
    }

}
