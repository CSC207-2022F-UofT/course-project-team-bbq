package delete_flashcardset_use_case;

import dataAccess.FlashcardSetDataAccess;
import dataAccess.IFlashcardSetDataAccess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MainDeleteFlashcardSet {

    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("Delete a Flashcard Set");
        AtomicReference<CardLayout> cardLayout = new AtomicReference<>(new CardLayout());
        JPanel screens = new JPanel(cardLayout.get());
        application.add(screens);

        // Create the parts to plug into the Use Case+Entities engine
        IFlashcardSetDataAccess flashcardSetRepo;
        try {
            flashcardSetRepo = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        DelFlashcardSetOutputBoundary outputBoundary = new DelFlashcardSetPresenter();
        DelFlashcardSetInputBoundary interactor = new DelFlashcardSetInteractor(flashcardSetRepo, outputBoundary);
        DelFlashcardSetController controller = new DelFlashcardSetController(
                interactor
        );

        // Build the GUI, plugging in the parts
        DeletionScreen deletionScreen = new DeletionScreen(controller);
        screens.add(deletionScreen, "welcome");
        cardLayout.get().show(screens, "delete flashcard set");
        application.pack();

        // Keep the application running until the user successfully creates a flashcard set or cancels creation
        while (deletionScreen.isKeepFrame()) {
            application.setVisible(true);
        }
        application.dispose();
    }
}
