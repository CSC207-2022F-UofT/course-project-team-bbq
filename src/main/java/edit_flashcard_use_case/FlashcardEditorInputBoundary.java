package edit_flashcard_use_case;

/**
 * Flashcard editor input boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface FlashcardEditorInputBoundary {
    FlashcardEditorResponseModel edit(FlashcardEditorRequestModel requestModel);
}