package interface_adapters.presenters.exceptions;

/**
 * The FCSetEditFailed error.
 * An error that is thrown when edit flashcard set fails.
 * @author Thomas Shim
 */
public class EditFlashcardSetFailed extends RuntimeException{
    /**
     * Creates a new FCSetEditFailed object
     * @param error String that is outputted when error is thrown.
     */
    public EditFlashcardSetFailed(String error){ super(error); }
}