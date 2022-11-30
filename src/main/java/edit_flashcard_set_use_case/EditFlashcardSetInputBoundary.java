package edit_flashcard_set_use_case;

/**
 * Flashcard set editor input boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface EditFlashcardSetInputBoundary {
    EditFlashcardSetResponseModel edit(EditFlashcardSetRequestModel requestModel);
}