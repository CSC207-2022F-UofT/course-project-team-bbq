package Editor.Flashcard.screens;

import Editor.Flashcard.FlashcardEditorOutputBoundary;
import Editor.Flashcard.FlashcardEditorResponseModel;

public class FlashcardEditorPresenter implements FlashcardEditorOutputBoundary {

    @Override
    public FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard) {
        return flashcard;
    }

    @Override
    public FlashcardEditorResponseModel prepareFailView(String error) {
        throw new FlashcardEditFailed(error);
    }
}