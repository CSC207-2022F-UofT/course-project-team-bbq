package interface_adapters.presenters;

import edit_flashcard_set_use_case.EditFlashcardSetOutputBoundary;
import edit_flashcard_set_use_case.EditFlashcardSetResponseModel;
import interface_adapters.presenters.exceptions.EditFlashcardSetFailed;

/**
 * Flashcard set editor presenter.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class EditFlashcardSetPresenter implements EditFlashcardSetOutputBoundary {
    /**
     * Returns a FCSetEditorResponseModel with the given edited information and flashcard set id to present in the view.
     * @param responseModel A FCSetEditorResponseModel that contains the edited flashcard set information
     * @return The FCSetEditorResponseModel given.
     */
    //This may seem redundant in some sense because we are simply returning the flashcard set again, but we do this to
    //adhere to Clean Architecture principles.
    @Override
    public EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel responseModel) {
        return responseModel;
    }

    /**
     * Throws a FCSetEditFailed error with the given error message.
     * @param error an error message to show on the screen
     * @return No return statement but we have return type to adhere to Clean Architecture, the view catches this thrown
     * error.
     */
    @Override
    public EditFlashcardSetResponseModel prepareFailView(String error) {throw new EditFlashcardSetFailed(error);}
}