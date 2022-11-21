package delete_flashcardset_use_case;

// Use case (Red) layer

public interface DelFlashcardSetOutputBoundary {

    DelFlashcardSetResponseModel prepareSuccessView(String message);

    DelFlashcardSetResponseModel prepareFailView(String error) throws FlashcardSetNotFound;
}
