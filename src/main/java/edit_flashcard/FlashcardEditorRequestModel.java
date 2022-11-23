package edit_flashcard;

/**
 * Flashcard editor request model.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FlashcardEditorRequestModel {
    private final int flashcardId;
    private final String termEdit;
    private final String definitionEdit;

    /**
     * FlashcardEditorRequestModel constructor.
     * @param flashcardId id of the flashcard
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public FlashcardEditorRequestModel(int flashcardId, String term, String definition){
        this.flashcardId = flashcardId;
        termEdit = term;
        definitionEdit = definition;
    }

    /**
     * Get the id of this FlashcardEditorRequestModel.
     * @return id of the given flashcard
     */
    public int getFlashcardId() {
        return flashcardId;
    }

    /**
     * Get the definition of this FlashcardEditorRequestModel.
     * @return definition of the given flashcard
     */
    public String getDefinitionEdit() {
        return definitionEdit;
    }

    /**
     * Get the term of this FlashcardEditorRequestModel.
     * @return term of the given flashcard
     */
    public String getTermEdit() {
        return termEdit;
    }
}