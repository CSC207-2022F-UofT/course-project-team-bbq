package interface_adapters.presenters;

import create_flashcard_use_case.CreateFlashcardOutputBoundary;
import create_flashcard_use_case.CreateFlashcardResponseModel;
import interface_adapters.presenters.exceptions.CreateFlashcardFailed;

/**
 * Presenter for flashcard creation.
 * Interface adaptors.
 * @author Junyu Chen
 */
public class CreateFlashcardPresenter implements CreateFlashcardOutputBoundary {
    @Override
    public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public CreateFlashcardResponseModel prepareFailView(String error) {
        throw  new CreateFlashcardFailed(error);
    }
}
