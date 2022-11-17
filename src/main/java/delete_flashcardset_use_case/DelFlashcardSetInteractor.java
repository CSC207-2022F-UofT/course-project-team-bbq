package delete_flashcardset_use_case;

// Use case (Red) layer

import dataAccess.IFlashcardSetDataAccess;

/**
 * [Feature: Deleting a Flashcard Set]
 * UI:
 * - user provides the ID of the flashcard set to be deleted
 * - user is alerted if the specified flashcard set doesn't exist
 * - if the set exists, then user clicks on button to confirm deletion
 * User case:
 * - check that the flashcard set exists in the database (if not alert the user)
 * - if it exists, ask for confirmation to delete from the user
 * - notify successful deletion of flashcard set to the user
 */

public class DelFlashcardSetInteractor implements DelFlashcardSetInputBoundary {

    IFlashcardSetDataAccess flashcardSetDataAccess;
    DelFlashcardSetOutputBoundary outputBoundary;

    public DelFlashcardSetInteractor(IFlashcardSetDataAccess flashcardSetDataAccess, DelFlashcardSetOutputBoundary outputBoundary) {
        this.flashcardSetDataAccess = flashcardSetDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public DelFlashcardSetResponseModel delete(DelFlashcardSetRequestModel requestModel) {

        if (flashcardSetDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return outputBoundary.prepareFailView("Flashcard set #"
                    + requestModel.getFlashcardSetId() + " doesn't exist.");
        } else {

            // delete the flashcard set in the database
            flashcardSetDataAccess.deleteFlashcardSet(requestModel.getFlashcardSetId());

            return outputBoundary.prepareSuccessView("Flashcard set #" + requestModel.getFlashcardSetId()
                    + " has been deleted.");
        }
    }
}
