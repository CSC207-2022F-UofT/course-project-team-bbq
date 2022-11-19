package studyMode;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatDarculaLaf;

import dataAccess.*;
import studyMode.screens.StudySettingsScreen;

import java.io.IOException;

public class MockMain {

    public static void main(String[] args) throws IOException {
        // only use one
        FlatIntelliJLaf.setup(); // light mode
        // FlatDarculaLaf.setup(); // dark mode


        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
//        IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, null);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
        StudySessionController controller = new StudySessionController(interactor);

        new StudySettingsScreen(controller, 0);
//        new StudySettingsScreen(controller, 1);
    }
}
