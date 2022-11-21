package flashcardCreator;


public class FcCController {
    FcCInputBoundary inputBoundary;
    int flashcardSetId;
    public FcCController(FcCInputBoundary inputBoundary, int flashcardSetId){
        this.inputBoundary = inputBoundary;
        this.flashcardSetId = flashcardSetId;
    }
    public FcCResponseModel create(String term, String definition){
        return inputBoundary.create(new FcCRequestModel(flashcardSetId, term, definition));
    }
}
