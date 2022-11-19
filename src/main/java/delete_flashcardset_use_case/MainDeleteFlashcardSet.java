package delete_flashcardset_use_case;

import dataAccess.FlashcardDataAccess;
import dataAccess.FlashcardSetDataAccess;
import dataAccess.IFlashcardDataAccess;
import dataAccess.IFlashcardSetDataAccess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MainDeleteFlashcardSet {

    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("Delete a Flashcard Set");
        application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AtomicReference<CardLayout> cardLayout = new AtomicReference<>(new CardLayout());
        JPanel screens = new JPanel(cardLayout.get());
        application.add(screens);

        // Create the parts to plug into the Use Case+Entities engine
        IFlashcardSetDataAccess flashcardSetRepo;
        IFlashcardDataAccess flashcardRepo;
        try {
            flashcardSetRepo = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            flashcardRepo = new FlashcardDataAccess("src/data/Flashcards.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        DelFlashcardSetOutputBoundary outputBoundary = new DelFlashcardSetPresenter();
        DelFlashcardSetInputBoundary interactor = new DelFlashcardSetInteractor(flashcardSetRepo, flashcardRepo,
                outputBoundary);
        DelFlashcardSetController controller = new DelFlashcardSetController(
                interactor
        );

        // Build the GUI, plugging in the parts
        int flashcardSetId = 0;  // placeholder id of the flashcard set to be deleted
        DeletionScreen deletionScreen = new DeletionScreen(flashcardSetId, controller, application);
        screens.add(deletionScreen, "welcome");
        cardLayout.get().show(screens, "delete flashcard set");
        application.pack();
        application.setVisible(true);
    }
}
