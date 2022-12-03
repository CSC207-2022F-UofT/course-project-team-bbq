package create_flashcard_use_case;
/**
 * Presenter for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */
public interface CreateFlashcardOutputBoundary {
    CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel);
    CreateFlashcardResponseModel prepareFailView(String error);
    CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel);
}
