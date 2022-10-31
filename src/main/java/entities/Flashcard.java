package entities;

import java.time.*;

public class Flashcard {
    private static int count;

    private String term;
    private String definition;
    private LocalDateTime creationDate;
    private int FlashcardId;
    private int FlashcardSetId;


    public Flashcard(String term, String definition, LocalDateTime creationDate){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.FlashcardId = ++count;
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
        return FlashcardId;
    }

    public int getFlashcardSetId() {
        return FlashcardSetId;
    }

    public void setFlashcardSetId(int flashcardSetId) {
        FlashcardSetId = flashcardSetId;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
