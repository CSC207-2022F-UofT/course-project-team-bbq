package create_flashcardset_use_case;

// Use case (Red) layer

public interface FlashcardSetInputBoundary {
    FlashcardSetResponseModel create(FlashcardSetRequestModel requestModel) throws FlashcardSetCreationFailed;
}
