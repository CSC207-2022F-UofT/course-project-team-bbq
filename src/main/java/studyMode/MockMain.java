package studyMode;

import entities.FlashcardFactory;
import entities.FlashcardSet;
import entities.FlashcardSetFactory;

public class MockMain {

    public static void main(String[] args) {
        FlashcardFactory cardFactory = new FlashcardFactory();
        FlashcardSetFactory setFactory = new FlashcardSetFactory();

        FlashcardSet flashcards = setFactory.create("Test Set", "", true, 0,
                                                    "Lucas");
    }
}
