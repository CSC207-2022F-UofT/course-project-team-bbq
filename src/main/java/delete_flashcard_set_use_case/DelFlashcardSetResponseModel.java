package delete_flashcard_set_use_case;

// Use case (Red) layer


/**
 * The response model with the delete message when a flashcard set is deleted.
 *
 * @author Edward Ishii
 */
public class DelFlashcardSetResponseModel {

    private String message;

    /**
     * Constructs a DelFlashcardSetResponseModel.
     *
     * @param message the message to be displayed when a flashcard set is deleted.
     */
    public DelFlashcardSetResponseModel(String message) {
        this.message = message;
    }

    /**
     * Getter for the delete message.
     *
     * @return the message displayed when a flashcard set is deleted.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Setter for the delete message.
     *
     * @param message the new delete message to be displayed.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
