package edit_flashcard_use_case.screens;

/**
 * The FlashcardEditFailed error.
 * An error that is thrown when edit flashcard fails.
 * @author Thomas Shim
 */
public class FlashcardEditFailed extends RuntimeException{
    /**
     * Creates a new FlashcardEditFailed object
     * @param error String that is outputted when error is thrown.
     */
    public FlashcardEditFailed(String error){
        super(error);
    }
}