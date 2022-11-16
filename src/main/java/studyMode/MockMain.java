package studyMode;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatDarculaLaf;

import dataAccess.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockMain {

    public static void main(String[] args) throws IOException {
        // only use one
        // FlatIntelliJLaf.setup(); // light mode
        FlatDarculaLaf.setup(); // dark mode


        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
//        IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, null);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
        StudySessionController controller = new StudySessionController(interactor);
//        MockSettingsView settingsView = new MockSettingsView(controller);
//        MockStudyView studyView = new MockStudyView(controller);
//
//        int numFlashcards = settingsView.eventHandler(0);
//        studyView.eventHandler(numFlashcards);

        new StudySettingsScreen(controller, 0);
//        new StudySettingsScreen(controller, 1);
    }
}
