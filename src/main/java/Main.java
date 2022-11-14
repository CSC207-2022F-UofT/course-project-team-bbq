import entityRequestModels.QuizSettingsDsGateway;

import quiz_settings_use_case.*;
import dataAccess.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // DATABASE
//        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
//        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
//
//        // QUIZ SETTINGS USE CASE
//        QuizSettingsOutputBoundary presenter = new QuizSettingsPresenter();
//        QuizSettingsInputBoundary interactor = new QuizSettingsInteractor(gateway, presenter);
//        StudySessionController controller = new StudySessionController(interactor);
////        MockSettingsView settingsView = new MockSettingsView(controller);
////        MockStudyView studyView = new MockStudyView(controller);
////
////        int numFlashcards = settingsView.eventHandler(0);
////        studyView.eventHandler(numFlashcards);
//
//        new QuizSettingsScreen(controller, 0);
    }
}
