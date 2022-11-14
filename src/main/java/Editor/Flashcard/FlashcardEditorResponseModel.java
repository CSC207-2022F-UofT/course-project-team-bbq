package Editor.Flashcard;

public class FlashcardEditorResponseModel {
    private int flashcardId;
    private String termEdit;
    private String definitionEdit;

    public FlashcardEditorResponseModel(int flashcardId, String term, String definition){
        this.flashcardId = flashcardId;
        termEdit = term;
        definitionEdit = definition;
    }

    public int getFlashcardId() {
        return flashcardId;
    }

    public String getDefinitionEdit() {
        return definitionEdit;
    }

    public String getTermEdit() {
        return termEdit;
    }
}
