package delete_flashcard_set_use_case;

import frameworks_and_drivers.database.DBGateway;

// Use case (Red) layer

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
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetInteractor implements DeleteFlashcardSetInputBoundary {

    final DBGateway dbGateway;

    final DeleteFlashcardSetOutputBoundary outputBoundary;

    /**
     * Constructs the use case interactor.
     *
     * @param dbGateway      the database to modify so that the flashcard set is deleted.
     * @param outputBoundary the output boundary that prepares the success/fail view of deleting flashcard sets.
     */
    public DeleteFlashcardSetInteractor(DBGateway dbGateway, DeleteFlashcardSetOutputBoundary outputBoundary) {
        this.dbGateway = dbGateway;
        this.outputBoundary = outputBoundary;
    }

//    /**
//     * Alternative constructor for testing purposes.
//     *
//     * @param flashcardSetDataAccess the flashcard set database.
//     * @param flashcardDataAccess    the flashcard database.
//     * @param outputBoundary         the output boundary for preparing success/fail view.
//     */
//    public DelFlashcardSetInteractor(IFlashcardSetDataAccess flashcardSetDataAccess,
//                                     IFlashcardDataAccess flashcardDataAccess,
//                                     DelFlashcardSetOutputBoundary outputBoundary) {
//        this.flashcardSetDataAccess = flashcardSetDataAccess;
//        this.flashcardDataAccess = flashcardDataAccess;
//        this.outputBoundary = outputBoundary;
//    }

    /**
     * Delete the flashcard set (and all the flashcards contained within it) from the database.
     *
     * @param requestModel the request model required for flashcard set deletion (i.e., the id).
     * @return the response model that contains the deletion message.
     */
    @Override
    public DeleteFlashcardSetResponseModel delete(DeleteFlashcardSetRequestModel requestModel) {

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
