package search_use_case;

import entities.FlashcardSet;

import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary{

    final SearchPresenter presenter;

    // figure out what else needs to be here
    public SearchInteractor(SearchPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public SearchResponseModel create(SearchRequestModel requestModel){

        ArrayList<FlashcardSet> result_set = new ArrayList<FlashcardSet>();
        // populate result_set with all possible flashcards from database


        // search algorithm
        ArrayList<String> tags = requestModel.getTags();
        String input = requestModel.getSearch_input();



        if (result_set.size() > 0){
            // return presenter.prepareSuccessView()
        }

        return presenter.prepareFailView("No Flashcard Sets matched your search criteria, please try again.");

    }
}
