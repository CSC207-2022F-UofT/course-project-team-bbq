package create_flashcard_set_use_case;

// Use case (Red) layer

import entities.FlashcardSet;

public class FlashcardSetResponseModel {
    private final FlashcardSet fs;

    public FlashcardSetResponseModel(FlashcardSet fs) {
        this.fs = fs;
    }

    public FlashcardSet getFs() {
        return fs;
    }

}
