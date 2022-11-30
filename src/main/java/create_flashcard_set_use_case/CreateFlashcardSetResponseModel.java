package create_flashcard_set_use_case;

import entities.FlashcardSet;

// Use case (Red) layer

/**
 * The response model containing data from the newly created flashcard set.
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetResponseModel {
    private final FlashcardSet fs;

    /**
     * Constructs a FlashcardSetResponseModel.
     *
     * @param fs the flashcard set.
     */
    public CreateFlashcardSetResponseModel(FlashcardSet fs) {
        this.fs = fs;
    }

    /**
     * Getter for the flashcard set.
     *
     * @return the flashcard set.
     */
    public FlashcardSet getFs() {
        return fs;
    }
}
