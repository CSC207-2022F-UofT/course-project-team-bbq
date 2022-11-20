package delete_flashcardset_use_case;

// Interface adapters (Green) layer

public class DelFlashcardSetController {

    DelFlashcardSetInputBoundary userInput;

    public DelFlashcardSetController(DelFlashcardSetInputBoundary userInput) {
        this.userInput = userInput;
    }

    DelFlashcardSetResponseModel delete(int flashcardSetId) {
        DelFlashcardSetRequestModel requestModel = new DelFlashcardSetRequestModel(flashcardSetId);

        return userInput.delete(requestModel);
    }
}
