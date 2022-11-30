package edit_flashcard_use_case;

/**
 * Flashcard editor request model.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class EditFlashcardRequestModel {
    private final int flashcardId;
    private final String termEdit;
    private final String definitionEdit;

    /**
     * Creates a new FlashcardEditorRequestModel.
     * @param flashcardId id of the flashcard
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public EditFlashcardRequestModel(int flashcardId, String term, String definition){
        this.flashcardId = flashcardId;
        termEdit = term;
        definitionEdit = definition;
    }

    /**
     * Returns the id of this FlashcardEditorRequestModel.
     * @return id of this FlashcardEditorRequestModel
     */
    public int getFlashcardId() {
        return flashcardId;
    }

    /**
     * Returns the definition of this FlashcardEditorRequestModel.
     * @return definition of this FlashcardEditorRequestModel
     */
    public String getDefinitionEdit() {
        return definitionEdit;
    }

    /**
     * Returns the term of this FlashcardEditorRequestModel.
     * @return term of this FlashcardEditorRequestModel
     */
    public String getTermEdit() {
        return termEdit;
    }
}