package FlashcardRemover;

import dataAccess.FlashcardSetDataAccess;

public class FcRController {
    FcRInputBoundary inputBoundary;
    public FcRController(FcRInputBoundary inputBoundary, int flashcardSetId){
        this.inputBoundary = inputBoundary;
    }
    public FcRResponseModel delete(int flashcardSetId, int flashcardId){
        return inputBoundary.delete(new FcRRequestModel(flashcardSetId, flashcardId));
    }
}
