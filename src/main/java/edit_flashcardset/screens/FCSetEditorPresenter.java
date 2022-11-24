package edit_flashcardset.screens;

import edit_flashcardset.FCSetEditorOutputBoundary;
import edit_flashcardset.FCSetEditorResponseModel;

/**
 * Flashcard set editor presenter.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class FCSetEditorPresenter implements FCSetEditorOutputBoundary {
    /**
     * Returns a FCSetEditorResponseModel with the given edited information and flashcard set id to present in the view.
     * @param responseModel A FCSetEditorResponseModel that contains the edited flashcard set information
     * @return The FCSetEditorResponseModel given.
     */
    //This may seem redundant in some sense because we are simply returning the flashcard set again, but we do this to
    //adhere to Clean Architecture principles.
    @Override
    public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel) {
        return responseModel;
    }

    /**
     * Throws a FCSetEditFailed error with the given error message.
     * @param error an error message to show on the screen
     * @return No return statement but we have return type to adhere to Clean Architecture, the view catches this thrown
     * error.
     */
    @Override
    public FCSetEditorResponseModel prepareFailView(String error) {throw new FCSetEditFailed(error);}
}