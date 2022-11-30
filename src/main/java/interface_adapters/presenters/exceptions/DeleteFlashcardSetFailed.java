package interface_adapters.presenters.exceptions;

/**
 * An exception class for when flashcard set deletion fails, specifically, the id of flashcard set to be
 * deleted does not exist in the database.
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetFailed extends RuntimeException {

    /**
     * Throws a FlashcardSetNotFound error.
     *
     * @param error message of the error.
     */
    public DeleteFlashcardSetFailed(String error) {
        super(error);
    }
}
