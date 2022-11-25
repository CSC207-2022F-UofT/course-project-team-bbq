package search_use_case;

import dataAccess.*;
import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import login_and_signup_use_case.UserLoginResponseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * The Search Interactor
 * <p>
 * Application Business Rules.
 * @author Winston Chieng
 */
public class SearchInteractor implements SearchInputBoundary{

    private final SearchOutputBoundary presenter;
    private final DBGateway gateway;

    /**
     * Creates a SearchInteractor Object
     * @param presenter an object that receives data and updates the view
     * @param gateway gateway to give the class data to function
     */
    public SearchInteractor(SearchOutputBoundary presenter, DBGateway gateway){
        this.presenter = presenter;
        this.gateway = gateway;
    }

    /**
     * Create a SearchResponseModel using the requestModel information
     * @param requestModel a data-structure containing the user input, search tags, and current user
     * @return SearchResponseModel that contains the result set of FlashcardSets from the user search
     */
    @Override
    public SearchResponseModel create(SearchRequestModel requestModel) throws IOException {

        ArrayList<FlashcardSetDsRequestModel> result_set = new ArrayList<>();
        ArrayList<Integer> flashcard_set_ids = new ArrayList<>();

        // populate result_set with all possible flashcards from database
        UserLoginResponseModel curr_user = requestModel.getUser();

        Collection<CommonUserDsRequestModel> all_users = gateway.getUserGateway().getAllUsers();
        for (CommonUserDsRequestModel user : all_users){
            flashcard_set_ids.addAll(user.getFlashcardSetIds());
        }

        // Search algorithm

        // Search through all flashcards
        ArrayList<String> tags = requestModel.getTags();
        String input = requestModel.getSearch_input();
        if (input.equals("GET_ALL")) {
            for (Integer x : flashcard_set_ids) {
                if (!gateway.getFlashcardSet(x).getIsPrivate()|| curr_user.getIsAdmin()) {
                    result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                }
            }
        }
        // Search through flashcards using user input
        else{
            for (Integer x : flashcard_set_ids){
                if (!gateway.getFlashcardSet(x).getIsPrivate() || curr_user.getIsAdmin()){
                    for(String tag : tags){
                        if(tag.equals("Title") && gateway.getFlashcardSet(x).getTitle().contains(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Description") && gateway.getFlashcardSet(x).getDescription().contains(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Owner") && gateway.getFlashcardSet(x).getOwnerUsername().contains(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                    }
                }

            }
        }

        // Return results if there is at least 1 result
        if (result_set.size() > 0){
            SearchResponseModel searchResponseModel = new SearchResponseModel(result_set);
            return presenter.prepareSuccessView(searchResponseModel);
        }
        if (tags.size() > 0 && !input.equals("")){
            return presenter.prepareFailView("There are currently no FlashcardSets in the database.");
        }
        // User chooses no tags
        if (tags.size() == 0){
            return presenter.prepareFailView("Please select at least 1 tag.");
        }
        // User provides no input
        return presenter.prepareFailView("Please enter a keyword to search.");
        // User search unsuccessful
    }
}
