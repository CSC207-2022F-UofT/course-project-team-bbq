import dataAccess.*;
import entityRequestModels.CommonUserDsRequestModel;
import search_use_case.*;

import java.io.IOException;
import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarculaLaf;


public class searchTestMain {
    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup(); // dark mode

        IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());

        // Search requirements
        DBGateway dbGateway = new DBGateway(null, flashcardSetGateway, userDataAccess);

        SearchOutputBoundary search_presenter = new SearchPresenter();
        SearchInputBoundary search_interactor = new SearchInteractor(search_presenter, dbGateway);
        SearchController search_controller = new SearchController(search_interactor);
        ArrayList<Integer> setIDs = new ArrayList<>();
        CommonUserDsRequestModel user = new CommonUserDsRequestModel("jemp", "pio",
                true, setIDs);

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, null);

        new SearchScreen(search_controller, gateway, user);

    }



}
