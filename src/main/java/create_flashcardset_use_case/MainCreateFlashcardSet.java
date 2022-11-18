package create_flashcardset_use_case;

import dataAccess.FlashcardSetDataAccess;
import dataAccess.IFlashcardSetDataAccess;
import entities.FlashcardSetFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainCreateFlashcardSet {

    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("Create a Flashcard Set");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create the parts to plug into the Use Case+Entities engine
        IFlashcardSetDataAccess flashcardSetRepo;
        try {
            flashcardSetRepo = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetPresenter();
        FlashcardSetFactory flashcardSetFactory = new FlashcardSetFactory();
        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(flashcardSetRepo, outputBoundary,
                flashcardSetFactory);
        FlashcardSetController flashcardSetController = new FlashcardSetController(
                interactor
        );

        // Build the GUI, plugging in the parts
        CreationScreen creationScreen = new CreationScreen(flashcardSetController);
        screens.add(creationScreen, "welcome");
        cardLayout.show(screens, "create flashcard set");
        application.pack();

        // Keep the application running until the user successfully creates a flashcard set or cancels creation
        while (creationScreen.isKeepFrame()) {
            application.setVisible(true);
        }
        application.dispose();
    }
}
