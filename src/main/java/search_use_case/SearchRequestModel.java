package search_use_case;
import login_and_signup_use_case.UserLoginResponseModel;

import java.util.ArrayList;

/**
 * A data structure containing the search input
 * from the user, the selected tags, and the
 * current user searching.
 * <p>
 * Application Business Rules.
 * @author Winston Chieng
 */
public class SearchRequestModel {

    private final String search_input;
    private final ArrayList<String> tags;
    private final UserLoginResponseModel user;

    /**
     *  creates a SearchRequestModel
     * @param search_input the user input
     * @param tags  the selected tags
     * @param user  the current user
     */
    public SearchRequestModel(String search_input, ArrayList<String> tags, UserLoginResponseModel user){
        this.search_input = search_input;
        this.tags = tags;
        this.user = user;
    }

    /**
     *
     * @return the search input
     */
    String getSearch_input(){
        return search_input;
    }

    /**
     *
     * @return the ArrayList of tags selected by the user
     */
    ArrayList<String> getTags(){
        return tags;
    }

    /**
     *
     * @return the current user
     */
    UserLoginResponseModel getUser(){
        return user;
    }

}
