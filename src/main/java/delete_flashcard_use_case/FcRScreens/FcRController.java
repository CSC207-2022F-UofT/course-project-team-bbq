package delete_flashcard_use_case.FcRScreens;

import delete_flashcard_use_case.FcRInputBoundary;
import delete_flashcard_use_case.FcRRequestModel;
import delete_flashcard_use_case.FcRResponseModel;

/**
 * Controller for flashcard Removal.
 * Interface adaptors.
 * @author Junyu Chen
 */
public class FcRController {
    FcRInputBoundary inputBoundary;

    /**
     * Create FcRController.
     * @param inputBoundary interactor for flashcard remover.
     */
    public FcRController(FcRInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Removing flashcard form flashcard set.
     * @param flashcardSetId id of the flashcard set that flashcard will be removed from
     * @param flashcardId id of flashcard that will be removed
     * @return Success or failure view.
     */
    public FcRResponseModel delete(int flashcardSetId, int flashcardId){
        return inputBoundary.delete(new FcRRequestModel(flashcardSetId, flashcardId));
    }
}
