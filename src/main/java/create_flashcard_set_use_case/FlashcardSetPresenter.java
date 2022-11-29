package create_flashcard_set_use_case;

// Interface adapters (Green) layer

/**
 * The presenter that updates the view when creating a flashcard set succeeds or fails.
 *
 * @author Edward Ishii
 */
public class FlashcardSetPresenter implements FlashcardSetOutputBoundary {

    /**
     * Prepares the success view when a flashcard set is created and saved.
     *
     * @param fs the flashcard set that has been created.
     * @return the response model containing the saved flashcard set data.
     */
    @Override
    public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs) {
        return fs;
    }

    /**
     * Prepares the fail view when a flashcard set cannot be created or saved.
     *
     * @param error the failure message.
     * @return the response model containing the failure message.
     */
    @Override
    public FlashcardSetResponseModel prepareFailView(String error) {
        throw new FlashcardSetCreationFailed(error);
    }
}
