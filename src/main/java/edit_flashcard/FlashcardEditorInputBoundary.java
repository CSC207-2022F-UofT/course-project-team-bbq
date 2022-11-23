package edit_flashcard;

/**
 * Flashcard editor input boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface FlashcardEditorInputBoundary {
    /**
     * Edits the flashcard with the given flashcard information that contains the new edits.
     * @param requestModel the user's input in form of FlashcardEditorRequestModel.
     * @return a FlashcardEditorResponseModel which contains output information that is to be presented.
     */
    FlashcardEditorResponseModel edit(FlashcardEditorRequestModel requestModel);
}