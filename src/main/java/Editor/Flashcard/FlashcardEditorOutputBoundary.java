package Editor.Flashcard;

public interface FlashcardEditorOutputBoundary {
    FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard);
    FlashcardEditorResponseModel prepareFailView(String error);
}
