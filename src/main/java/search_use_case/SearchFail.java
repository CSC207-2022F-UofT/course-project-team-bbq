package search_use_case;

public class SearchFail extends RuntimeException{
    public SearchFail(String error){
        super(error);
    }
}
