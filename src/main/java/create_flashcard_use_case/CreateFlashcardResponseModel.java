package create_flashcard_use_case;

import java.time.LocalDateTime;
/**
 * Response model for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */
public class CreateFlashcardResponseModel {
    private final LocalDateTime creationDate;
    private String term, definition;
    private int flashcardId;
    private final Boolean existsDuplicate;

    /**
     *Creating CreateFlashcardResponseModel for flashcard creation
     * @param creationDate date of creation
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     * @param flashcardId flashcard id of the flashcard
     * @param existsDuplicate if there is duplicate flashcard currently
     */
    public CreateFlashcardResponseModel(LocalDateTime creationDate, String term, String definition, int flashcardId, Boolean existsDuplicate) {
        this.creationDate = creationDate;
        this.term = term;
        this.definition = definition;
        this.flashcardId = flashcardId;
        this.existsDuplicate = existsDuplicate;

    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getDefinition() {
        return definition;
    }

    public String getTerm() {
        return term;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getFlashcardId() {
        return flashcardId;
    }

    public Boolean existsDuplicate() {
        return existsDuplicate;
    }
}
