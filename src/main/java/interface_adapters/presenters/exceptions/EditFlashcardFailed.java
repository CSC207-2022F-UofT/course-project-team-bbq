package interface_adapters.presenters.exceptions;

/**
 * The FlashcardEditFailed error.
 * An error that is thrown when edit flashcard fails.
 * @author Thomas Shim
 */
public class EditFlashcardFailed extends RuntimeException{
    /**
     * Creates a new FlashcardEditFailed object
     * @param error String that is outputted when error is thrown.
     */
    public EditFlashcardFailed(String error){
        super(error);
    }
}