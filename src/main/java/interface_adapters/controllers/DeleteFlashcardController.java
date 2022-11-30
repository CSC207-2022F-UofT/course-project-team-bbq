package interface_adapters.controllers;

import delete_flashcard_use_case.DeleteFlashcardInputBoundary;
import delete_flashcard_use_case.DeleteFlashcardRequestModel;
import delete_flashcard_use_case.DeleteFlashcardResponseModel;

/**
 * Controller for flashcard Removal.
 * Interface adaptors.
 * @author Junyu Chen
 */
public class DeleteFlashcardController {
    DeleteFlashcardInputBoundary inputBoundary;

    /**
     * Create FcRController.
     * @param inputBoundary interactor for flashcard remover.
     */
    public DeleteFlashcardController(DeleteFlashcardInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Removing flashcard form flashcard set.
     * @param flashcardSetId id of the flashcard set that flashcard will be removed from
     * @param flashcardId id of flashcard that will be removed
     * @return Success or failure view.
     */
    public DeleteFlashcardResponseModel delete(int flashcardSetId, int flashcardId){
        return inputBoundary.delete(new DeleteFlashcardRequestModel(flashcardSetId, flashcardId));
    }
}
