import dataAccess.*;
import entityRequestModels.CommonUserDsRequestModel;
import quizUseCase.*;
import search_use_case.*;
import studyMode.*;

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

        // Study session requirements
        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, null);
        StudySessionOutputBoundary study_presenter = new StudySessionPresenter();
        StudySessionInputBoundary study_interactor = new StudySessionInteractor(gateway, study_presenter);
        StudySessionController study_controller = new StudySessionController(study_interactor);

        // QUIZ SETTINGS USE CASE
        QuizOutputBoundary quiz_presenter = new QuizPresenter();
        QuizInputBoundary quiz_interactor = new QuizInteractor(gateway, quiz_presenter);
        QuizController quiz_controller = new QuizController(quiz_interactor);


        new SearchScreen(search_controller, study_controller, quiz_controller, user);

    }



}
