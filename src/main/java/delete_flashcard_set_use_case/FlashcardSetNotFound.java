package delete_flashcard_set_use_case;

/**
 * An exception class for when flashcard set deletion fails, specifically, the id of flashcard set to be
 * deleted does not exist in the database.
 *
 * @author Edward Ishii
 */
public class FlashcardSetNotFound extends RuntimeException {

    /**
     * Throws a FlashcardSetNotFound error.
     *
     * @param error message of the error.
     */
    public FlashcardSetNotFound(String error) {
        super(error);
    }
}
