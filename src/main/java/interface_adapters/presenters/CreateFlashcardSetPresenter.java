package interface_adapters.presenters;

// Interface adapters (Green) layer

import create_flashcard_set_use_case.CreateFlashcardSetOutputBoundary;
import create_flashcard_set_use_case.CreateFlashcardSetResponseModel;
import interface_adapters.presenters.exceptions.CreateFlashcardSetFailed;

/**
 * The presenter that updates the view when creating a flashcard set succeeds or fails.
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetPresenter implements CreateFlashcardSetOutputBoundary {

    /**
     * Prepares the success view when a flashcard set is created and saved.
     *
     * @param fs the flashcard set that has been created.
     * @return the response model containing the saved flashcard set data.
     */
    @Override
    public CreateFlashcardSetResponseModel prepareSuccessView(CreateFlashcardSetResponseModel fs) {
        return fs;
    }

    /**
     * Prepares the fail view when a flashcard set cannot be created or saved.
     *
     * @param error the failure message.
     * @return the response model containing the failure message.
     */
    @Override
    public CreateFlashcardSetResponseModel prepareFailView(String error) {
        throw new CreateFlashcardSetFailed(error);
    }
}
