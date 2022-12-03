package edit_flashcard_use_case;

/**
 * Flashcard editor output boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface EditFlashcardOutputBoundary {
    EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard);
    EditFlashcardResponseModel prepareFailView(String error);
}