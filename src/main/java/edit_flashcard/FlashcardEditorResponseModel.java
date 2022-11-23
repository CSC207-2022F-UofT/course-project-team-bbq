package edit_flashcard;

/**
 * Flashcard editor response model.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FlashcardEditorResponseModel {
    private final int flashcardId;
    private final String termEdit;
    private final String definitionEdit;

    /**
     * FlashcardEditorResponseModel constructor.
     * @param flashcardId id of the flashcard
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public FlashcardEditorResponseModel(int flashcardId, String term, String definition){
        this.flashcardId = flashcardId;
        termEdit = term;
        definitionEdit = definition;
    }

    /**
     * Get the id of this FlashcardEditorResponseModel.
     * @return id of the new edited flashcard
     */
    public int getFlashcardId() {
        return flashcardId;
    }

    /**
     * Get the definition of this FlashcardEditorResponseModel.
     * @return definition of the new edited flashcard
     */
    public String getDefinitionEdit() {
        return definitionEdit;
    }

    /**
     * Get the term of this FlashcardEditorResponseModel.
     * @return term of the new edited flashcard
     */
    public String getTermEdit() {
        return termEdit;
    }
}