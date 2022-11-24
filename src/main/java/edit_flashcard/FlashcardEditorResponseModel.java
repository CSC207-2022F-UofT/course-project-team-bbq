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
     * Creates a new FlashcardEditorResponseModel object.
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
     * Returns the id of this FlashcardEditorResponseModel.
     * @return id of the new edited flashcard
     */
    public int getFlashcardId() {
        return flashcardId;
    }

    /**
     * Returns the definition of this FlashcardEditorResponseModel.
     * @return definition of the new edited flashcard
     */
    public String getDefinitionEdit() {
        return definitionEdit;
    }

    /**
     * Returns the term of this FlashcardEditorResponseModel.
     * @return term of the new edited flashcard
     */
    public String getTermEdit() {
        return termEdit;
    }
}