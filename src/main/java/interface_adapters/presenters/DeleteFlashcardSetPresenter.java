package interface_adapters.presenters;

// Interface adapters (Green) layer

import delete_flashcard_set_use_case.DeleteFlashcardSetOutputBoundary;
import delete_flashcard_set_use_case.DeleteFlashcardSetResponseModel;
import interface_adapters.presenters.exceptions.DeleteFlashcardSetFailed;

/**
 * The presenter that updates the view when deleting a flashcard set succeeds or fails.
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetPresenter implements DeleteFlashcardSetOutputBoundary {

    /**
     * Prepares the success view when flashcard set deletion succeeds.
     *
     * @param message the confirmation of deletion message.
     * @return the response model containing the deletion message.
     */
    @Override
    public DeleteFlashcardSetResponseModel prepareSuccessView(String message) {
        return new DeleteFlashcardSetResponseModel(message);
    }

    /**
     * Prepares the failure view when flashcard set deletion fails.
     *
     * @param error the error message.
     * @return the response model containing the error message.
     * @throws DeleteFlashcardSetFailed the error thrown when the flashcard set to be deleted is not found in the database.
     */
    @Override
    public DeleteFlashcardSetResponseModel prepareFailView(String error) {
        throw new DeleteFlashcardSetFailed(error);
    }
}
