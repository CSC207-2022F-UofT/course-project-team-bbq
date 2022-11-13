package Editor;

import entities.Flashcard;

public class FlashcardEditorRequestModel {
    private int flashcardId;
    private String termEdit;
    private String definitionEdit;

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
