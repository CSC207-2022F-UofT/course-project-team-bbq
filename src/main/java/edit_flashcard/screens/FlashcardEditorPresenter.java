package edit_flashcard.screens;

import edit_flashcard.FlashcardEditorOutputBoundary;
import edit_flashcard.FlashcardEditorResponseModel;

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