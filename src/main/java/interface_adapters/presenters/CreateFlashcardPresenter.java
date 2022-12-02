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
    /**
     * When creation is successful.
     * @param responseModel presenting information of the flashcard that was successfully created
     * @return the responseModel
     */
    @Override
    public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel) {
        return responseModel;
    }

    /**
     * When creation failed because of an error.
     * @param error error that occurred
     * @return the error
     */
    @Override
    public CreateFlashcardResponseModel prepareFailView(String error) {
        throw  new CreateFlashcardFailed(error);
    }

    /**
     * When creation failed because of duplicate terms.
     * @param responseModel presenting information of the flashcard that already existed
     * @return the responseModel
     */
    @Override
    public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
        return responseModel;
    }
}
