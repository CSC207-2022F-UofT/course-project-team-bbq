package create_flashcard_use_case.fcCScreens;

import create_flashcard_use_case.FcCInputBoundary;
import create_flashcard_use_case.FcCRequestModel;
import create_flashcard_use_case.FcCResponseModel;

/**
 * Controller for flashcard creation.
 * Interface adaptors.
 * @author Junyu Chen
 */

public class FcCController {
    FcCInputBoundary inputBoundary;
    int flashcardSetId;

    /**
     * Create FcCController
     * @param inputBoundary Interactor for flashcard creator.
     * @param flashcardSetId ID of flashcard set which new flashcard will be created in.
     */
    public FcCController(FcCInputBoundary inputBoundary, int flashcardSetId){
        this.inputBoundary = inputBoundary;
        this.flashcardSetId = flashcardSetId;
    }
    public FcCResponseModel create(String term, String definition){
        return inputBoundary.create(new FcCRequestModel(flashcardSetId, term, definition));
    }
}
