package create_flashcardset_use_case;

// Interface adapters (Green) layer

public class FlashcardSetController {

    FlashcardSetInputBoundary userInput;

    public FlashcardSetController(FlashcardSetInputBoundary userInput) {
        this.userInput = userInput;
    }

    FlashcardSetResponseModel create(String title, String description, boolean isPrivate, String username,
                                     String action) {
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel(title, description,
                isPrivate, username, action);

        return userInput.create(requestModel);
    }
}
