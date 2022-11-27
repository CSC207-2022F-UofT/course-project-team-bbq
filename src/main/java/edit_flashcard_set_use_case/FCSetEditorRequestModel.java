package edit_flashcard_set_use_case;

/**
 * Flashcard set editor request model.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FCSetEditorRequestModel {
    private final int flashcardSetId;
    private final String titleEdit;
    private final String descriptionEdit;

    /**
     * Creates a new FCSetEditorRequestModel
     * @param flashcardSetId the id of the flashcard set
     * @param titleEdit the title of the flashcard set
     * @param descriptionEdit the description of the flashcard set
     */
    public FCSetEditorRequestModel(int flashcardSetId, String titleEdit, String descriptionEdit){
        this.flashcardSetId = flashcardSetId;
        this.titleEdit = titleEdit;
        this.descriptionEdit = descriptionEdit;
    }

    /**
     * Returns the flashcard set id of this FCSetEditorRequestModel.
     * @return flashcard set id of this FCSetEditorRequestModel.
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    /**
     * Returns the title of this FCSetEditorRequestModel.
     * @return title of this FCSetEditorRequestModel.
     */
    public String getTitleEdit() {
        return titleEdit;
    }

    /**
     * Returns the description of this FCSetEditorRequestModel.
     * @return description of this FCSetEditorRequestModel.
     */
    public String getDescriptionEdit() {
        return descriptionEdit;
    }
}