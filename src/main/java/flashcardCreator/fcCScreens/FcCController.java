package flashcardCreator.fcCScreens;

import flashcardCreator.FcCInputBoundary;
import flashcardCreator.FcCRequestModel;
import flashcardCreator.FcCResponseModel;

/**
 * Controller for flashcard creator.
 * Interface adaptors.
 * @author Junyu Chen
 */

public class FcCController {
    FcCInputBoundary inputBoundary;
    int flashcardSetId;

    /**
     * Create FcCController
     * @param inputBoundary Interactor for flashcard creator.
     * @param flashcardSetId Id of flashcard set which new flashcard will be created in.
     */
    public FcCController(FcCInputBoundary inputBoundary, int flashcardSetId){
        this.inputBoundary = inputBoundary;
        this.flashcardSetId = flashcardSetId;
    }
    public FcCResponseModel create(String term, String definition){
        return inputBoundary.create(new FcCRequestModel(flashcardSetId, term, definition));
    }
}
