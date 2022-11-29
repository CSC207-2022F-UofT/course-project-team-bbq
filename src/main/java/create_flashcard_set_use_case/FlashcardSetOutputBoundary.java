package create_flashcard_set_use_case;

// Use case (Red) layer

/**
 * The output boundary interface that updates the view when creating a flashcard set succeeds or fails.
 *
 * @author Edward Ishii
 */

public interface FlashcardSetOutputBoundary {
    /**
     * Prepares the success view when a flashcard set is created and saved.
     *
     * @param fs the flashcard set that has been created.
     * @return the response model containing the saved flashcard set data.
     */
    FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs);

    /**
     * Prepares the fail view when a flashcard set cannot be created or saved.
     *
     * @param error the failure message.
     * @return the response model containing the failure message.
     * @throws FlashcardSetCreationFailed the error thrown when flashcard set creation fails.
     */
    FlashcardSetResponseModel prepareFailView(String error) throws FlashcardSetCreationFailed;
}
