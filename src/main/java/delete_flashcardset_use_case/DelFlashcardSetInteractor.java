package delete_flashcardset_use_case;

// Use case (Red) layer

import dataAccess.IFlashcardDataAccess;
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
    IFlashcardDataAccess flashcardDataAccess;  // for deleting flashcards associated with the flashcard set
    DelFlashcardSetOutputBoundary outputBoundary;

    public DelFlashcardSetInteractor(IFlashcardSetDataAccess flashcardSetDataAccess,
                                     IFlashcardDataAccess flashcardDataAccess,
                                     DelFlashcardSetOutputBoundary outputBoundary) {
        this.flashcardSetDataAccess = flashcardSetDataAccess;
        this.flashcardDataAccess = flashcardDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public DelFlashcardSetResponseModel delete(DelFlashcardSetRequestModel requestModel) {

        if (flashcardSetDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return outputBoundary.prepareFailView("Flashcard set #"
                    + requestModel.getFlashcardSetId() + " doesn't exist.");
        } else {
            // First delete all the flashcards associated with this flashcard set in Flashcards.csv
            for (int id : flashcardSetDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds()) {
                flashcardDataAccess.deleteFlashcard(id);
            }
            // Then delete the flashcard set in FlashcardSets.csv
            flashcardSetDataAccess.deleteFlashcardSet(requestModel.getFlashcardSetId());

            return outputBoundary.prepareSuccessView("Flashcard set #" + requestModel.getFlashcardSetId()
                    + " has been deleted.");
        }
    }
}
