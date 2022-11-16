import com.formdev.flatlaf.FlatIntelliJLaf;

import quizUseCase.*;
import dataAccess.*;
import quizUseCase.screens.QuizSettingsScreen;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FlatIntelliJLaf.setup();

        // DATABASE
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        // IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, null);

        // QUIZ SETTINGS USE CASE
        QuizOutputBoundary presenter = new QuizPresenter();
        QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
        QuizController controller = new QuizController(interactor);

        new QuizSettingsScreen(controller, 0);
    }
}
