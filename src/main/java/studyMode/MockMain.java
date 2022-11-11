package studyMode;

import entities.Flashcard;
import entities.FlashcardFactory;
import entities.FlashcardSet;
import entities.FlashcardSetFactory;

import java.io.IOException;
import java.time.LocalDateTime;

public class MockMain {

    public static void main(String[] args) throws IOException {
        FlashcardFactory cardFactory = new FlashcardFactory();
        FlashcardSetFactory setFactory = new FlashcardSetFactory();

        FlashcardSet flashcards = setFactory.create("Test Set", "", true, 0,
                                                    "Lucas");

        for (int i=0; i<3; i++){
            Flashcard card = cardFactory.create("Test Card " + i, "definition " + i, LocalDateTime.now(),
                                i, 0);
            flashcards.addFlashcard(card);
        }
        FlashcardStudierFactory studierFactory = new FlashcardStudierFactory();
        FlashcardStudier studier = studierFactory.create(flashcards, true);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(studier, presenter);
        StudySessionController controller = new StudySessionController(interactor);
        MockView view = new MockView(controller);
        boolean quit = false;

        while (!quit){
            quit = view.eventHandler();
        }
    }
}
