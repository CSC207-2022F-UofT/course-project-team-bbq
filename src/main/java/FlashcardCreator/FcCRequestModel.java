package FlashcardCreator;

public class FcCRequestModel {
    private int flashcardSetId;
    private String term, definition;
    public FcCRequestModel(int flashcardSetId, String term, String definition){
        this.definition = definition;
        this.term = term;
        this.flashcardSetId = flashcardSetId;
    }

    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}
