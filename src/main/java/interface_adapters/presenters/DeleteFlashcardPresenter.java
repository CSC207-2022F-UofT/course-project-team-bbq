package interface_adapters.presenters;

import delete_flashcard_use_case.DeleteFlashcardOutputBoundary;
import delete_flashcard_use_case.DeleteFlashcardResponseModel;
import interface_adapters.presenters.exceptions.DeleteFlashcardFailed;

/**
 * Presenter for flashcard removal.
 * Interface adaptors.
 * @author Junyu Chen
 */

public class DeleteFlashcardPresenter implements DeleteFlashcardOutputBoundary {
    @Override
    public DeleteFlashcardResponseModel prepareSuccessView(DeleteFlashcardResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public DeleteFlashcardResponseModel prepareFailView(String error) {
        throw  new DeleteFlashcardFailed(error);
    }
}
