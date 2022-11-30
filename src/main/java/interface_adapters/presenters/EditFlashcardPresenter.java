package interface_adapters.presenters;

import edit_flashcard_use_case.EditFlashcardOutputBoundary;
import edit_flashcard_use_case.EditFlashcardResponseModel;
import interface_adapters.presenters.exceptions.EditFlashcardFailed;

/**
 * Flashcard editor presenter.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class EditFlashcardPresenter implements EditFlashcardOutputBoundary {
    /**
     * Returns a FlashcardEditorResponseModel with the given edited information and flashcard id to present in the view.
     * @param flashcard A FlashcardEditorResponseModel that contains the edited flashcard information
     * @return The FlashcardEditorResponseModel given.
     */
    //This may seem redundant in some sense because we are simply returning the flashcard again, but we do this to
    //adhere to Clean Architecture principles.
    @Override
    public EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard) {
        return flashcard;
    }

    /**
     * Throws a FlashcardEditFailed error with the given error message.
     * @param error an error message to show on the screen
     * @return No return statement but we have return type to adhere to Clean Architecture, the view catches this thrown
     * error.
     */
    @Override
    public EditFlashcardResponseModel prepareFailView(String error) {
        throw new EditFlashcardFailed(error);
    }
}