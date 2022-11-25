package edit_flashcardset;

/**
 * Flashcard set editor response model.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FCSetEditorResponseModel {
    private final int flashcardSetId;
    private final String titleEdit;
    private final String descriptionEdit;

    /**
     * Creates a new FCSetEditorResponseModel
     * @param flashcardSetId the id of the flashcard set
     * @param titleEdit the title of the flashcard set
     * @param descriptionEdit the description of the flashcard set
     */
    public FCSetEditorResponseModel(int flashcardSetId, String titleEdit, String descriptionEdit){
        this.flashcardSetId = flashcardSetId;
        this.titleEdit = titleEdit;
        this.descriptionEdit = descriptionEdit;
    }

    /**
     * Returns the flashcard set id of this FCSetEditorResponseModel.
     * @return flashcard set id of this FCSetEditorResponseModel.
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    /**
     * Returns the title of this FCSetEditorResponseModel.
     * @return title of this FCSetEditorRequestModel.
     */
    public String getTitleEdit() {
        return titleEdit;
    }

    /**
     * Returns the description of this FCSetEditorResponseModel.
     * @return description of this FCSetEditorResponseModel.
     */
    public String getDescriptionEdit() {
        return descriptionEdit;
    }
}