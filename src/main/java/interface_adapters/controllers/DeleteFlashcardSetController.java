package interface_adapters.controllers;

// Interface adapters (Green) layer

import delete_flashcard_set_use_case.DeleteFlashcardSetInputBoundary;
import delete_flashcard_set_use_case.DeleteFlashcardSetRequestModel;
import delete_flashcard_set_use_case.DeleteFlashcardSetResponseModel;

/**
 * The controller for flashcard set deletion.
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetController {

    final DeleteFlashcardSetInputBoundary interactor;

    /**
     * Constructs a new DelFlashcardSetController.
     *
     * @param interactor the use case interactor for deleting flashcard sets.
     */
    public DeleteFlashcardSetController(DeleteFlashcardSetInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Deletes a flashcard set (and all the flashcards contained within it) from the database.
     *
     * @param flashcardSetId the id of the flashcard set to be deleted.
     * @return the response model containing the deletion message.
     */
    public DeleteFlashcardSetResponseModel delete(int flashcardSetId) {
        DeleteFlashcardSetRequestModel requestModel = new DeleteFlashcardSetRequestModel(flashcardSetId);

        return interactor.delete(requestModel);
    }
}
