package interface_adapters.presenters.exceptions;

/**
 * An exception class for when flashcard set creation fails.
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetFailed extends RuntimeException {

    /**
     * Throws a FlashcardSetCreationFailed error.
     *
     * @param error message of the error.
     */
    public CreateFlashcardSetFailed(String error) {
        super(error);
    }
}
