package delete_flashcard_set_use_case;

// Interface adapters (Green) layer

/**
 * The controller for flashcard set deletion.
 *
 * @author Edward Ishii
 */
public class DelFlashcardSetController {

    DelFlashcardSetInputBoundary interactor;

    /**
     * Constructs a new DelFlashcardSetController.
     *
     * @param interactor the use case interactor for deleting flashcard sets.
     */
    public DelFlashcardSetController(DelFlashcardSetInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Deletes a flashcard set (and all the flashcards contained within it) from the database.
     *
     * @param flashcardSetId the id of the flashcard set to be deleted.
     * @return the response model containing the deletion message.
     */
    DelFlashcardSetResponseModel delete(int flashcardSetId) {
        DelFlashcardSetRequestModel requestModel = new DelFlashcardSetRequestModel(flashcardSetId);

        return interactor.delete(requestModel);
    }
}
