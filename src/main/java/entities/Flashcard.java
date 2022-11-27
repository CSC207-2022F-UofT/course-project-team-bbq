package entities;

import java.time.*;

public class Flashcard {
    private String term;
    private String definition;
    private final LocalDateTime creationDate;
    private final int flashcardId;
    private int belongsToId;


    public Flashcard(String term, String definition, LocalDateTime creationDate,
                     int flashcardId, int belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.belongsToId = belongsToId;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getFlashcardId() {
        return flashcardId;
    }

    public int getBelongsToId() {
        return belongsToId;
    }

    public void setBelongsToId(int belongsToId) {
        this.belongsToId = belongsToId;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
