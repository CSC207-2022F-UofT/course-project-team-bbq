package flashcardCreator;

import java.time.LocalDateTime;
/**
 * Response model for flashcard creator.
 * Application business rules.
 * @author Junyu Chen
 */
public class FcCResponseModel {
    private final LocalDateTime creationDate;
    private String term, definition;

    /**
     * Create FcCResponseModel
     * @param creationDate date of creation of the flashcard
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     */
    public FcCResponseModel(LocalDateTime creationDate, String term, String definition) {
        this.creationDate = creationDate;
        this.term = term;
        this.definition = definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setTerm(String term) {
        this.term = term;
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
}
