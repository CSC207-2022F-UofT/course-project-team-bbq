package edit_flashcardset.screens;

/**
 * The FCSetEditFailed error.
 * An error that is thrown when edit flashcard set fails.
 * @author Thomas Shim
 */
public class FCSetEditFailed extends RuntimeException{
    /**
     * Creates a new FCSetEditFailed object
     * @param error String that is outputted when error is thrown.
     */
    public FCSetEditFailed(String error){ super(error); }
}