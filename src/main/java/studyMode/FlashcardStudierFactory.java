package studyMode;

import entities.FlashcardSet;

public class FlashcardStudierFactory {

    public FlashcardStudier create(FlashcardSet flashcardSet, boolean defaultDisplay){
        return new FlashcardStudier(flashcardSet, defaultDisplay);
    }
}
