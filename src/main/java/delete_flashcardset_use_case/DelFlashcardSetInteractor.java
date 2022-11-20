package delete_flashcardset_use_case;

// Use case (Red) layer

import dataAccess.DBGateway;
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

    DBGateway dbGateway;
    IFlashcardSetDataAccess flashcardSetDataAccess;  // for testing
    IFlashcardDataAccess flashcardDataAccess;  // for testing
    DelFlashcardSetOutputBoundary outputBoundary;

    public DelFlashcardSetInteractor(DBGateway dbGateway, DelFlashcardSetOutputBoundary outputBoundary) {
        this.dbGateway = dbGateway;
        this.outputBoundary = outputBoundary;
    }

    // Alternative constructor for testing purposes
    public DelFlashcardSetInteractor(IFlashcardSetDataAccess flashcardSetDataAccess,
                                     IFlashcardDataAccess flashcardDataAccess,
                                     DelFlashcardSetOutputBoundary outputBoundary) {
        this.flashcardSetDataAccess = flashcardSetDataAccess;
        this.flashcardDataAccess = flashcardDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public DelFlashcardSetResponseModel delete(DelFlashcardSetRequestModel requestModel) {

        if (dbGateway.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return outputBoundary.prepareFailView("Flashcard set #"
                    + requestModel.getFlashcardSetId() + " doesn't exist.");
        } else {
            // First delete all the flashcards associated with this flashcard set in Flashcards.csv
            for (int id : dbGateway.getFlashcardSetGateway().getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds()) {
                dbGateway.deleteFlashcard(requestModel.getFlashcardSetId(), id);
            }
            // Then delete the flashcard set in FlashcardSets.csv
            dbGateway.deleteFlashcardSet(dbGateway.getFlashcardSet(requestModel.getFlashcardSetId()).getOwnerUsername(),
                    requestModel.getFlashcardSetId());

            return outputBoundary.prepareSuccessView("Flashcard set #" + requestModel.getFlashcardSetId()
                    + " has been deleted.");
        }
    }
}
