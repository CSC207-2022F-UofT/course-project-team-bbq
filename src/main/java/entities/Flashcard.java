package entities;

import java.time.*;

public class Flashcard {

    private String term;
    private String definition;
    private LocalDateTime creationDate;
    private int flashcardId;
    private int flashcardSetId;


    public Flashcard(String term, String definition, LocalDateTime creationDate, int flashcardId, int flashcardSetId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.flashcardSetId = flashcardSetId;
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

    public int getFlashcardSetId() {
        return flashcardSetId;
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
}
