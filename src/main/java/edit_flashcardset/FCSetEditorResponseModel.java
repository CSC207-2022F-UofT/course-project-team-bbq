package edit_flashcardset;

public class FCSetEditorResponseModel {
    private final int flashcardSetId;
    private final String titleEdit;
    private final String descriptionEdit;

    public FCSetEditorResponseModel(int flashcardSetId, String titleEdit, String descriptionEdit){
        this.flashcardSetId = flashcardSetId;
        this.titleEdit = titleEdit;
        this.descriptionEdit = descriptionEdit;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public String getTitleEdit() {
        return titleEdit;
    }

    public String getDescriptionEdit() {
        return descriptionEdit;
    }
}