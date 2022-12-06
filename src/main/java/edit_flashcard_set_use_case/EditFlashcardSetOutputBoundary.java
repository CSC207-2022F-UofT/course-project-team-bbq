package edit_flashcard_set_use_case;

/**
 * Flashcard set editor output boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface EditFlashcardSetOutputBoundary {
    EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel responseModel);

    EditFlashcardSetResponseModel prepareFailView(String error);
}