package create_flashcard_set_use_case;

/**
 * An exception class for when flashcard set creation fails.
 *
 * @author Edward Ishii
 */
public class FlashcardSetCreationFailed extends RuntimeException {

    /**
     * Throws a FlashcardSetCreationFailed error.
     *
     * @param error message of the error.
     */
    public FlashcardSetCreationFailed(String error) {
        super(error);
    }
}
