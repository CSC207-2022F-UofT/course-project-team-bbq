package edit_flashcard;

/**
 * Flashcard editor input boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface FlashcardEditorInputBoundary {
    FlashcardEditorResponseModel edit(FlashcardEditorRequestModel requestModel);
}