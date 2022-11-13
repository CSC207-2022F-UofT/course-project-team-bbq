package Editor;

public interface FlashcardEditorOutputBoundary {
    FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard);
    FlashcardEditorResponseModel prepareFailView(String error);
}
