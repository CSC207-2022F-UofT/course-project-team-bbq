package delete_flashcard_set_use_case;

// Interface adapters (Green) layer

/**
 * The presenter that updates the view when deleting a flashcard set succeeds or fails.
 *
 * @author Edward Ishii
 */
public class DelFlashcardSetPresenter implements DelFlashcardSetOutputBoundary {

    /**
     * Prepares the success view when flashcard set deletion succeeds.
     *
     * @param message the confirmation of deletion message.
     * @return the response model containing the deletion message.
     */
    @Override
    public DelFlashcardSetResponseModel prepareSuccessView(String message) {
        return new DelFlashcardSetResponseModel(message);
    }

    /**
     * Prepares the failure view when flashcard set deletion fails.
     *
     * @param error the error message.
     * @return the response model containing the error message.
     * @throws FlashcardSetNotFound the error thrown when the flashcard set to be deleted is not found in the database.
     */
    @Override
    public DelFlashcardSetResponseModel prepareFailView(String error) {
        throw new FlashcardSetNotFound(error);
    }
}
