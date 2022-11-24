package edit_flashcard.screens;

import edit_flashcard.FlashcardEditorOutputBoundary;
import edit_flashcard.FlashcardEditorResponseModel;

/**
 * Flashcard editor presenter.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class FlashcardEditorPresenter implements FlashcardEditorOutputBoundary {
    /**
     * Returns a FlashcardEditorResponseModel with the given edited information and flashcard id to present in the view.
     * @param flashcard A FlashcardEditorResponseModel that contains the edited flashcard information
     * @return The FlashcardEditorResponseModel given.
     */
    //This may seem redundant in some sense because we are simply returning the flashcard again, but we do this to
    //adhere to Clean Architecture principles.
    @Override
    public FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard) {
        return flashcard;
    }

    /**
     * Throws a FlashcardEditFailed error with the given error message.
     * @param error an error message to show on the screen
     * @return No return statement but we have return type to adhere to Clean Architecture, the view catches this thrown
     * error.
     */
    @Override
    public FlashcardEditorResponseModel prepareFailView(String error) {
        throw new FlashcardEditFailed(error);
    }
}