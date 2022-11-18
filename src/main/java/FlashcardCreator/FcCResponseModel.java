package FlashcardCreator;

import java.time.LocalDateTime;

public class FcCResponseModel {
    LocalDateTime creationDate;
    String term, definition;

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
