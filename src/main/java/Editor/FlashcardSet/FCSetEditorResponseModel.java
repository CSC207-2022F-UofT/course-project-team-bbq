package Editor.FlashcardSet;

public class FCSetEditorResponseModel {
    private int flashcardSetId;
    private String titleEdit;
    private String descriptionEdit;

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