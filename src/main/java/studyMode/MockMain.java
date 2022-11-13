package studyMode;

import dataAccess.DBGateway;
import entities.Flashcard;
import entities.FlashcardFactory;
import entities.FlashcardSet;
import entities.FlashcardSetFactory;

import java.io.IOException;
import java.time.LocalDateTime;

public class MockMain {

    public static void main(String[] args) throws IOException {


        DBGateway gateway = new DBGateway(null, null, null);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
        StudySessionController controller = new StudySessionController(interactor);
        MockSettingsView settingsView = new MockSettingsView(controller);
        MockStudyView studyView = new MockStudyView(controller);
        boolean quit = false;

        int numFlashcards = settingsView.eventHandler(0);
        while (!quit){
            quit = studyView.eventHandler(numFlashcards);
        }
    }
}
