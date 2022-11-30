package delete_flashcard_use_case;
/**
 * Presenter for flashcard removal.
 * Application business rules.
 * @author Junyu Chen
 */

public interface DeleteFlashcardOutputBoundary {
    DeleteFlashcardResponseModel prepareSuccessView(DeleteFlashcardResponseModel responseModel);

    DeleteFlashcardResponseModel prepareFailView(String error);
}
