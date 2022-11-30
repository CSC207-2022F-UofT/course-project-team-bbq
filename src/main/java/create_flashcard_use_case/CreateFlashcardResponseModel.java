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

    /**
     * Create FcCResponseModel
     * @param creationDate date of creation of the flashcard
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public CreateFlashcardResponseModel(LocalDateTime creationDate, String term, String definition, int flashcardId) {
        this.creationDate = creationDate;
        this.term = term;
        this.definition = definition;
        this.flashcardId = flashcardId;

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
}
