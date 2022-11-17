package search_use_case;

import dataAccess.*;
import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SearchInteractor implements SearchInputBoundary{

    final SearchPresenter presenter;

    // figure out what else needs to be here
    public SearchInteractor(SearchPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public SearchResponseModel create(SearchRequestModel requestModel) throws IOException {

        ArrayList<FlashcardSetDsRequestModel> result_set = new ArrayList<>();
        ArrayList<Integer> flashcard_set_ids = new ArrayList<>();

        // populate result_set with all possible flashcards from database
        IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
        DBGateway dbGateway = new DBGateway(null, flashcardSetDataAccess, userDataAccess);
        CommonUserDsRequestModel curr_user = requestModel.getUser();

        // change to dbGateway.getAllUsers()
        Collection<CommonUserDsRequestModel> all_users = userDataAccess.getAllUsers();
        for (CommonUserDsRequestModel user : all_users){
            flashcard_set_ids.addAll(user.getFlashcardSetIds());
        }

        // search algorithm
        ArrayList<String> tags = requestModel.getTags();
        String input = requestModel.getSearch_input();
        if (input.equals("GET_ALL")) {
            for (Integer x : flashcard_set_ids) {
                if (!dbGateway.getFlashcardSet(x).getIsPrivate()|| curr_user.getIsAdmin()) {
                    result_set.add(flashcardSetDataAccess.getFlashcardSet(x));
                }
            }
        }
        else{
            for (Integer x : flashcard_set_ids){
                if (!dbGateway.getFlashcardSet(x).getIsPrivate() || curr_user.getIsAdmin()){
                    for(String tag : tags){
                        if(tag.equals("Title") && dbGateway.getFlashcardSet(x).getTitle().equals(input)){
                            result_set.add(flashcardSetDataAccess.getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Description") && dbGateway.getFlashcardSet(x).getDescription().equals(input)){
                            result_set.add(flashcardSetDataAccess.getFlashcardSet(x));
                            break;
                        }
                        if(tag.equals("Owner") && dbGateway.getFlashcardSet(x).getOwnerUsername().equals(input)){
                            result_set.add(flashcardSetDataAccess.getFlashcardSet(x));
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
