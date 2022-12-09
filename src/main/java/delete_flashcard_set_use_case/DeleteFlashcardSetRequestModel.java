package delete_flashcard_set_use_case;

// Use case (Red) layer

/**
 * The request model required for deleting a flashcard set.
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetRequestModel {

    private final int flashcardSetId;

    /**
     * Constructs the request model.
     *
     * @param flashcardSetId the id of the flashcard set to be deleted.
     */
    public DeleteFlashcardSetRequestModel(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }

    /**
     * Getter for the flashcard set id.
     *
     * @return the id of this flashcard set.
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }
}
