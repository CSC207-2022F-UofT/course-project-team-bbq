package entities;

import java.time.*;

public class Flashcard {
    private String term;
    private String definition;
    private final LocalDateTime creationDate;
    private final int flashcardId;
    private final int belongsToId;


    /**
     * Creates a flashcard
     * @param term the flashcard's term
     * @param definition the definition of the flashcard's term
     * @param creationDate the time the flashcard was initially created at
     * @param flashcardId the flashcard's unique id
     * @param belongsToId the unique id of the flashcard set this flashcard belongs to
     */
    public Flashcard(String term, String definition, LocalDateTime creationDate,
                     int flashcardId, int belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.belongsToId = belongsToId;
    }

    /**
     * @return the flashcard's term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @return the flashcard's definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * @return the date the flashcard was initially created at
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return the flashcard's unique id
     */
    public int getFlashcardId() {
        return flashcardId;
    }

    /**
     * changes the flashcard'ss definition
     * @param definition the new definition of the flashcard
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * changes the flashcard's term
     * @param term the flashcard's new term
     */
    public void setTerm(String term) {
        this.term = term;
    }
}
