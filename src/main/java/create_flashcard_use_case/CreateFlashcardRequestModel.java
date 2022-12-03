package create_flashcard_use_case;
/**
 * Request model for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */


public class CreateFlashcardRequestModel {
    private int flashcardSetId;
    private String term, definition;

    /**
     * Create CreateFlashcardRequestModel
     * @param flashcardSetId id of flashcard set which new flashcard will be added to
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public CreateFlashcardRequestModel(int flashcardSetId, String term, String definition){
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
