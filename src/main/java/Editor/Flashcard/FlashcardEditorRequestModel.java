package Editor.Flashcard;


public class FlashcardEditorRequestModel {
    private final int flashcardId;
    private final String termEdit;
    private final String definitionEdit;

    public FlashcardEditorRequestModel(int flashcardId, String term, String definition){
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