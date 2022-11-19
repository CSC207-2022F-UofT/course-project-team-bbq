package search_use_case;

import dataAccess.*;
import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SearchInteractor implements SearchInputBoundary{

    private final SearchPresenter presenter;
    private final DBGateway gateway;

    // figure out what else needs to be here
    public SearchInteractor(SearchPresenter presenter, DBGateway gateway){
        this.presenter = presenter;
        this.gateway = gateway;
    }

    @Override
    public SearchResponseModel create(SearchRequestModel requestModel) throws IOException {

        ArrayList<FlashcardSetDsRequestModel> result_set = new ArrayList<>();
        ArrayList<Integer> flashcard_set_ids = new ArrayList<>();

        // populate result_set with all possible flashcards from database
        CommonUserDsRequestModel curr_user = requestModel.getUser();

        // change to dbGateway.getAllUsers()
        Collection<CommonUserDsRequestModel> all_users = gateway.getUserGateway().getAllUsers();
        for (CommonUserDsRequestModel user : all_users){
            flashcard_set_ids.addAll(user.getFlashcardSetIds());
        }

        // search algorithm
        ArrayList<String> tags = requestModel.getTags();
        String input = requestModel.getSearch_input();
        if (input.equals("GET_ALL")) {
            for (Integer x : flashcard_set_ids) {
                if (!gateway.getFlashcardSet(x).getIsPrivate()|| curr_user.getIsAdmin()) {
                    result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                }
            }
        }
        else{
            for (Integer x : flashcard_set_ids){
                if (!gateway.getFlashcardSet(x).getIsPrivate() || curr_user.getIsAdmin()){
                    for(String tag : tags){
                        if(tag.equals("Title") && gateway.getFlashcardSet(x).getTitle().equals(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Description") && gateway.getFlashcardSet(x).getDescription().equals(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Owner") && gateway.getFlashcardSet(x).getOwnerUsername().equals(input)){
                            result_set.add(gateway.getFlashcardSetGateway().getFlashcardSet(x));
                            break;
                        }
                    }
                }

            }
        }

        if (result_set.size() > 0){
            SearchResponseModel searchResponseModel = new SearchResponseModel(result_set);
            return presenter.prepareSuccessView(searchResponseModel);
        }
        if (tags.size() == 0){
            return presenter.prepareFailView("Please select at least 1 tag.");
        }
        if (input.equals("")){
            return presenter.prepareFailView("Please enter a keyword to search.");
        }
        return presenter.prepareFailView("No Flashcard Sets matched your search criteria, please try again.");

    }
}
