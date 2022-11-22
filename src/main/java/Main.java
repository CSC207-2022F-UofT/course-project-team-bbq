import com.formdev.flatlaf.FlatDarculaLaf;

import dataAccess.*;
import login_and_signup_use_case.login_and_signup_use_case_screens.WelcomeScreen;
import quiz_use_case.*;
import quiz_use_case.screens.QuizSettingsScreen;

import java.io.IOException;

/**
 * Run the main class to run the application.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // ONLY UNCOMMENT ONE THEME!!
        // FlatIntelliJLaf.setup(); // light mode
        FlatDarculaLaf.setup(); // dark mode

//        new WelcomeScreen();

        // WILL DELETE THIS CODE SECTION AFTER I FINISH TESTING
        IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
        DBGateway gateway = new DBGateway(flashcardDataAccess,
                flashcardSetDataAccess, userDataAccess);

        QuizOutputBoundary presenter = new QuizPresenter();
        QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
        QuizController controller = new QuizController(interactor);

        new QuizSettingsScreen(controller, 2);
    }
}
