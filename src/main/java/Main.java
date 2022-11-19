import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatDarculaLaf;

import loginAndSignupUseCase.loginAndSignupUseCaseScreens.WelcomeScreen;
import quizUseCase.*;
import dataAccess.*;
import quizUseCase.screens.QuizSettingsScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Run the main class to run the application.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // ONLY UNCOMMENT ONE THEME!!
        // FlatIntelliJLaf.setup(); // light mode
        FlatDarculaLaf.setup(); // dark mode

        // DATABASE
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        //Welcome Screen
        JFrame application = new JFrame("Welcome");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        WelcomeScreen welcome = new WelcomeScreen();
        screens.add(welcome, "Welcome");
        cardLayout.show(screens, "Welcome");
        application.pack();
        application.setVisible(true);

        // QUIZ SETTINGS USE CASE
        QuizOutputBoundary presenter = new QuizPresenter();
        QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
        QuizController controller = new QuizController(interactor);

        new QuizSettingsScreen(controller, 0);
    }
}
