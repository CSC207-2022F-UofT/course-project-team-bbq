package create_flashcard_set_use_case;

// Use case (Red) layer

public interface FlashcardSetOutputBoundary {
    FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs);

    FlashcardSetResponseModel prepareFailView(String error) throws FlashcardSetCreationFailed;
}
