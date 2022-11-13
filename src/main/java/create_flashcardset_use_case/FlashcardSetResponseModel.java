package create_flashcardset_use_case;

// Use case (Red) layer

import entities.FlashcardSet;

public class FlashcardSetResponseModel {
    private FlashcardSet fs;

    public FlashcardSetResponseModel(FlashcardSet fs) {
        this.fs = fs;
    }

    public FlashcardSet getFs() {
        return fs;
    }

    public void setFs(FlashcardSet fs) {
        this.fs = fs;
    }

}
